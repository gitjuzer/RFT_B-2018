import base64

from flask import json
from flask_testing import TestCase

from rft import app


class LocalServerTest(TestCase):

    def create_app(self):
        return app

    def setUp(self):
        # creates a test client
        self.app = app.test_client()
        # propagate the exceptions to the test client
        self.app.testing = True

    def tearDown(self):
        pass

    def test_home_status_code(self):
        # sends HTTP GET request to the application
        # on the specified path
        result = self.app.get('/')

        # assert the status code of the response
        self.assertEqual(result.status_code, 200)

    def test_home_data(self):
        result = self.app.get('/')
        data = json.loads(result.get_data(as_text=True))

        self.assertEqual(data['result'], "Test QUIZ Rest-API. Check the documentation!")

    def test_successful_authentication(self):
        result = self.app.get('/login?username=dummy_user&password=dummy_pass')
        data = json.loads(result.get_data(as_text=True))
        self.assertEqual(data['result'], 'Success')
        self.assertEqual(result.status_code, 200)

    def test_bad_credentials(self):
        result = self.app.get('/login?username=wrong_user&password=wrong_pass')
        data = json.loads(result.get_data(as_text=True))
        self.assertEqual(data['result'], 'Failed!')
        self.assertEqual(result.status_code, 200)

    def test_authorized_access(self):
        valid_credentials = base64.b64encode(b'dummy_user:dummy_pass').decode('utf-8')
        result = self.app.get('/getEmailAddress?username=dummy_user',
                              headers={'Authorization': 'Basic ' + valid_credentials})
        data = json.loads(result.get_data(as_text=True))
        expected_result = [{'email': 'dummy_email'}]
        self.assertEqual(data, expected_result)
        self.assertEqual(result.status_code, 200)

    def test_unauthorized_access(self):
        result = self.app.get('/getEmailAddress?username=dummy_user')
        data = json.loads(result.get_data(as_text=True))
        self.assertEqual(data['result'], 'You are not authorized!')
        self.assertEqual(result.status_code, 200)
        
    def test_high_scores(self):
        valid_credentials = base64.b64encode(b'dummy_user:dummy_pass').decode('utf-8')
        result = self.app.get('/getHighScores', headers={'Authorization': 'Basic ' + valid_credentials})
        data = json.loads(result.get_data(as_text=True))
        expected_result = {'username': 'dummy_user', 'difficulty': 'medium', 'point': 54}
        self.assertIn(expected_result, data)
        self.assertEqual(result.status_code, 200)
