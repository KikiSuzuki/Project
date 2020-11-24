package po;

import java.util.Date;

public class User {
        private int user_id;
        private String user_login;
        private String user_name;
        private int user_gender;
        private Date user_dob;
        private String user_question;
        private String user_answer;
        private String user_about;
        private String user_password;
        private String user_photopath;

    public String getUser_photopath() {
        return user_photopath;
    }

    public void setUser_photopath(String user_photopath) {
        this.user_photopath = user_photopath;
    }

    public String getUser_peer() {
        return user_peer;
    }

    public void setUser_peer(String user_peer) {
        this.user_peer = user_peer;
    }

    private String user_peer;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_gender() {
            return user_gender;
        }

        public void setUser_gender(int user_gender) {
            this.user_gender = user_gender;
        }

        public Date getUser_dob() {
            return user_dob;
        }

        public void setUser_dob(Date user_dob) {
            this.user_dob = user_dob;
        }

        public String getUser_question() {
            return user_question;
        }

        public void setUser_question(String user_question) {
            this.user_question = user_question;
        }

        public String getUser_answer() {
            return user_answer;
        }

        public void setUser_answer(String user_answer) {
            this.user_answer = user_answer;
        }

        public String getUser_about() {
            return user_about;
        }

        public void setUser_about(String user_about) {
            this.user_about = user_about;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }
}
