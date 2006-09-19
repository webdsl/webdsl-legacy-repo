class UserInfo
{
    private String username;
    private String fullname;
    private String password;
    private String passwordcheck;
    private String email;
    private String url;


    public String getUsername() {
	return username;
    }

    public String getFullname() {
	return fullname;
    }

    public String getPassword() {
	return password;
    }

    public String getPasswordcheck() {
	return passwordcheck;
    }

    public String getEmail() {
	return email;
    }

    public String getUrl() {
	return url;
    }


    public void setUsername (String username) {
	this.username = username;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public void setPassword (String password) {
	this.password = password;
    }

    public void setPasswordcheck (String passwordcheck) {
	this.passwordcheck = passwordcheck;
    }

    public void setEmail (String email) {
	this.email = email;
    }

    public void setUrl (String url) {
	this.Url = url;
    }

    public boolean passwordIsConsistent() {
	return this.getPassword().equals(this.getPasswordcheck());
    }
}
