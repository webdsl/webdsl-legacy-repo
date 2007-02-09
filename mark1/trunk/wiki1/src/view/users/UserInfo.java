package webdsl.users;

public class UserInfo
{
    private String username;
    private String fullname;
    private String password;
    private String passwordcheck;
    private String email;
    private String url;

    public UserInfo() 
    {
	username = "";
	fullname = "";
	password = "";
	passwordcheck = "";
	email = "";
	url = "";
    }

    // getProperty

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

    // hasProperty

    public boolean hasUsername(String x)
    {
	return username != null && username.equals(x);
    }

    public boolean hasUsername()
    {
	return hasValue(getUsername());
    }

    public boolean hasFullname() {
	return hasValue(fullname);
    }
    
    public boolean hasPassword() {
	return hasValue(password);
    }
    
    public boolean hasPasswordcheck() {
	return hasValue(passwordcheck);
    }
    
    public boolean hasEmail() {
	return hasValue(email);
    }
    
    public boolean hasUrl() {
	return hasValue(url);
    }
    
    // setProperty

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
	this.url = url;
    }


    public void reset()
    {
	setUsername(null);
	setFullname(null);
	setPassword(null);
	setPasswordcheck(null);
	setEmail(null);
	setUrl(null);
    }


    public boolean passwordIsConsistent() {
    	return this.getPassword() != null
	    && this.getPasswordcheck() != null
	    && this.getPassword().equals(this.getPasswordcheck());
    }

    public boolean isComplete() {
	return hasValue(getUsername())
	    && hasValue(getFullname())
	    && hasValue(getPassword())
	    && hasValue(getPasswordcheck())
	    && hasValue(getEmail())
	    && passwordIsConsistent();
    }

    public boolean isPartlyComplete() 
    {
	return hasValue(getUsername())
	    || hasValue(getFullname())
	    || hasValue(getPassword())
	    || hasValue(getPasswordcheck())
	    || hasValue(getEmail());
    }

    public static boolean hasValue(String x)
    {
	return x != null && (!x.trim().equals(""));
    }

    public static boolean sameValues(String x, String y)
    {
	return x == y
	    || (x != null 
		&& y != null
		&& (!x.equals(y)));
    }

}
