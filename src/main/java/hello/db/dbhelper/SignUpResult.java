package hello.db.dbhelper;

public enum SignUpResult {
    USERNAME_IS_TAKEN,
    EMAIL_IS_TAKEN,
    //consider it
    WEAK_PASSWORD,
    SUCCESS
}
