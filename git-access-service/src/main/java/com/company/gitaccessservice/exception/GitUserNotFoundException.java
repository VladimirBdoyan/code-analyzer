package com.company.gitaccessservice.exception;

public class GitUserNotFoundException extends RuntimeException {

        public GitUserNotFoundException(String login) {
            super(String.format("Login by :  {%s}  - Not found", login));
        }

}


