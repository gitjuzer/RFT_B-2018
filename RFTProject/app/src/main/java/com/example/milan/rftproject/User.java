package com.example.milan.rftproject;

public class User {

        private int user_id;
        private String username;
        private String password;

        public void User(int user_id,String username,String password) {
            this.user_id=user_id;
            this.username = username;
            this.password = password;
        }

        protected int getUser_id()
        {
            return user_id;
        }

        protected String getUsername()
        {
            return username;
        }

        protected String getPassword()
        {
            return password;
        }

        protected void setUserid(int user_id)
        {
            this.user_id=user_id;
        }

        protected void setUsername(String username)
        {
            this.username=username;
        }

        protected void setPassword(String password)
        {
            this.password=password;
        }
    }

