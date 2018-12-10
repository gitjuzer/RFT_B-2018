package com.example.milan.rftproject;

public class User {
        private String response;
        private int user_id;
        private String username;
        private String password;
        private String email;

        public void User(int user_id,String username,String password,String email) {
            this.user_id=user_id;
            this.username = username;
            this.password = password;
            this.email=email;
        }

        public String getResponse() {
            return response;
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

        protected String getEmail()
    {
        return email;
    }

        protected void setUserid(int user_id)
        {
            this.user_id=user_id;
        }

        protected void setEmail(String email){this.email=email;}

        protected void setUsername(String username)
        {
            this.username=username;
        }

        protected void setPassword(String password)
        {
            this.password=password;
        }
    }

