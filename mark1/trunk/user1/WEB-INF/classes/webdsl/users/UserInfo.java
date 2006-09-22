package webdsl.users;

public class UserInfo
{
    private String username;
    private String fullname;
    private String password;
    private String passwordcheck;
    private String email;
    private String url;

    private boolean changed;
    private boolean change;

    public UserInfo() {}

    public boolean hasUsername()
    {
	return hasValue(getUsername());
    }

    public boolean hasUsername(String x)
    {
	return username != null && username.equals(x);
    }

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

    public boolean isChanged() {
	return changed;
    }

    public boolean getChange() {
	return change;
    }


    public void setUsername (String username) {
	if(!sameValues(getUsername(), username))
	    setChanged(true);
	this.username = username;
    }

    public void setFullname(String fullname) {
	if(!sameValues(getFullname(), fullname))
	    setChanged(true);
	this.fullname = fullname;
    }

    public void setPassword (String password) {
	if(!sameValues(getPassword(), password))
	    setChanged(true);
	this.password = password;
    }

    public void setPasswordcheck (String passwordcheck) {
	if(!sameValues(getPasswordcheck(), passwordcheck))
	    setChanged(true);
    	this.passwordcheck = passwordcheck;
    }

    public void setEmail (String email) {
	if(!sameValues(getEmail(), email))
	    setChanged(true);
	this.email = email;
    }

    public void setUrl (String url) {
	if(!sameValues(getUrl(), url))
	    setChanged(true);
	this.url = url;
    }

    public void setChanged(boolean changed) {
	this.changed = changed;
    }

    public void setChange(boolean change) {
	this.change = change;
    }

    public void makeEmpty()
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

    public void partiallyUpdateFrom(UserInfo userinfo)
    {
	if (hasValue(userinfo.getUsername()))
	    setUsername(userinfo.getUsername());
	
	if (hasValue(userinfo.getFullname()))
	    setFullname(userinfo.getFullname());

	if (hasValue(userinfo.getPassword()))
	    setPassword(userinfo.getPassword());

	if (hasValue(userinfo.getPasswordcheck()))
	    setPasswordcheck(userinfo.getPasswordcheck());

	if (hasValue(userinfo.getEmail()))
	    setEmail(userinfo.getEmail());

	if (hasValue(userinfo.getUrl()))
	    setUrl(userinfo.getUrl());

	if (userinfo.isChanged())
	    setChanged(true);
    }
}
