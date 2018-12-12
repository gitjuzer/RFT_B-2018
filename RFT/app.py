import logging.handlers
import random
import sqlite3
from functools import wraps

from flask import Flask, jsonify, request

app = Flask(__name__)
handler = logging.handlers.RotatingFileHandler(
    'log.txt',
    maxBytes=1024 * 1024)
logging.getLogger('werkzeug').setLevel(logging.DEBUG)
logging.getLogger('werkzeug').addHandler(handler)
app.logger.setLevel(logging.WARNING)
app.logger.addHandler(handler)

DATABASE = 'rft.db'


def requires_authentication(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        auth = request.authorization
        if not auth or not check_auth(auth.username, auth.password):
            return jsonify(result="You are not authorized!")
        return f(*args, **kwargs)

    return decorated


def check_auth(username, password):
    connection = sqlite3.connect(DATABASE)
    result = connection.execute("select * from user where username=? and password=?",
                                (username, password)).fetchall()
    connection.close()
    if len(result) == 1:
        return jsonify(result="Success"), 200
    else:
        return jsonify(result="Failed!"), 200


@app.route('/')
def test():
    return jsonify(result="Test QUIZ Rest-API. Check the documentation!")


@app.route('/register', methods=['GET', 'POST'])
def register():
    username = request.args.get('username')
    password = request.args.get('password')
    email = request.args.get('email')
    if username is None or password is None or email is None:
        return jsonify(result="Missing parameters!")
    else:
        connection = sqlite3.connect(DATABASE)
        connection.execute("insert into User values(?, ?, ?, ?)", (random.getrandbits(8), username, password, email))
        connection.commit()
        connection.close()
        return jsonify(result="New User has been added to the database!"), 200


@app.route('/login')
def login():
    username = request.args.get('username')
    password = request.args.get('password')
    return check_auth(username, password)


@app.route('/getEmailAddress')
@requires_authentication
def get_email_address():
    username = request.args.get('username')
    connection = sqlite3.connect(DATABASE)
    connection.row_factory = sqlite3.Row
    result = connection.execute("select email from User where username == ?", [username]).fetchall()
    connection.close()
    return jsonify([dict(column_name) for column_name in result]), 200


@app.route('/newHighScore')
@requires_authentication
def new_score():
    uid = request.args.get('uid')
    difficulty = request.args.get('difficulty')
    point = request.args.get('point')
    if point is None or point is None or point is None:
        return jsonify(result="Missing parameters!")
    else:
        connection = sqlite3.connect(DATABASE)
        connection.execute("insert into Highscore values(?, ?, ?, ?)", (random.getrandbits(8), uid, difficulty, point))
        connection.commit()
        connection.close()
        return jsonify(result="New User has been added to the database!"), 200


@app.route('/getHighScores')
@requires_authentication
def get_scores():
    connection = sqlite3.connect(DATABASE)
    connection.row_factory = sqlite3.Row
    query = "SELECT User.username, point, difficulty FROM Highscore inner join User on User.id == Highscore.user_id" \
            " order by difficulty desc, point desc"
    data = connection.execute(query).fetchall()
    connection.close()
    return jsonify([dict(column_name) for column_name in data]), 200


@app.route('/getPersonalHighScore')
@requires_authentication
def get_personal_scores():
    username = request.args.get('username')
    connection = sqlite3.connect(DATABASE)
    connection.row_factory = sqlite3.Row
    data = connection.execute(
        "SELECT User.username, point, difficulty FROM Highscore inner join User on User.id == Highscore.user_id "
        "where User.username = ? order by difficulty desc, point desc",
        [username]).fetchall()
    connection.close()
    return jsonify([dict(column_name) for column_name in data]), 200


@app.route('/getTenRandomQuestion')
@requires_authentication
def get_ten_random_question():
    connection = sqlite3.connect(DATABASE)
    connection.row_factory = sqlite3.Row
    query = "SELECT question, category, wrong_1, wrong_2, wrong_3, correct FROM Question order by random() limit 10"
    data = connection.execute(query).fetchall()
    connection.close()
    return jsonify([dict(column_name) for column_name in data]), 200


@app.route('/addNewQuestion', methods=['GET', 'POST'])
@requires_authentication
def add_new_question():
    question = request.args.get('question')
    category = request.args.get('category')
    wrong_1 = request.args.get('wrong1')
    wrong_2 = request.args.get('wrong2')
    wrong_3 = request.args.get('wrong3')
    correct = request.args.get('correct')
    if question is None \
            or category is None \
            or wrong_1 is None \
            or wrong_2 is None \
            or wrong_3 is None \
            or correct is None:
        return jsonify(result="Missing parameters!")
    else:
        connection = sqlite3.connect(DATABASE)
        connection.execute("insert into Question values(?, ?, ?, ?, ?, ?, ?)",
                           (random.getrandbits(8), question, category, wrong_1, wrong_2, wrong_3, correct))
        connection.commit()
        connection.close()
        return jsonify(result="You have successfully added a new question!"), 200


if __name__ == '__main__':
    app.run(host='0.0.0.0')
