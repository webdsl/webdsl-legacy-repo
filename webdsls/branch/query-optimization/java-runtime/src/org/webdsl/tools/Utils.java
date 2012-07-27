package org.webdsl.tools;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.webdsl.WebDSLEntity;

public final class Utils {
    public static Object[] concatArrays(Object[] ar1, Object[] ar2) {
        List<Object> thelist = new ArrayList<Object>();
        for(Object o : ar1)
            thelist.add(o);
        for(Object o : ar2)
            thelist.add(o);
        return thelist.toArray();
    }

    /*public static boolean equal(int a, int b) {
        return a == b;
    }

    public static boolean equal(double a, double b) {
        return a == b;
    }

    public static boolean equal(int a, Integer b) {
        return b.equals(a);
    }

    public static boolean equal(double a, Double b) {
        return b.equals(a);
    }*/

    public static boolean equal(Object a, Object b) {
        if(a == null && b == null) {
            return true;
        } else if( (a == null && b != null) || (a != null && b == null) ) {
            return false;
        }
        return a.equals(b);
    }

    public static Object cast(Object e2 , Class<?> t) {
        return (e2 instanceof org.hibernate.proxy.HibernateProxy)?
            t.cast( ((org.hibernate.proxy.HibernateProxy) e2).getHibernateLazyInitializer().getImplementation())
          : t.cast(e2);
    }

    public static boolean isInstance(Object o, Class<?> c) {
        //org.hibernate.Hibernate.initialize(o);
        if(o instanceof WebDSLEntity) {
            return ((WebDSLEntity)o).isInstance(c);
        } else {
            return c.isInstance(o);
        }
    }

    public static String encodeIdList(Collection<?> c){
        String res = "";
        for(Object obj: c) {
            WebDSLEntity e = (WebDSLEntity) obj;
            res+=e.getId()+",";
        }
        return res.substring(0, Math.max(0,res.length()-1));
    }

    /**
     * http://forum.hibernate.org/viewtopic.php?p=2387828
     *
     * Utility method that tries to properly initialize the Hibernate CGLIB
     * proxy.
     *
     * @param <T>
     * @param maybeProxy -- the possible Hibernate generated proxy
     * @param baseClass -- the resulting class to be cast to.
     * @return the object of a class <T>
     * @throws ClassCastException
     */
    /*public <T> T deproxy(Object maybeProxy, Class<T> baseClass) throws ClassCastException {
       if (maybeProxy instanceof org.hibernate.proxy.HibernateProxy) {
          return baseClass.cast(((org.hibernate.proxy.HibernateProxy) maybeProxy).getHibernateLazyInitializer().getImplementation());
       }
       return baseClass.cast(maybeProxy);
    } */

    public static void download(FacesContext facesContext, byte[] file, String name, String type) {
        if (!facesContext.getResponseComplete()) {
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType(type);
            response.setContentLength(file.length);
            response.setHeader("Content-disposition","attachment; filename=" + name);
            ServletOutputStream out;
            try {
                out = response.getOutputStream();
                out.write(file);
                out.flush();
            } catch (IOException e) {
                //TODO: something
            }
            facesContext.responseComplete();
        }
    }

    public static boolean containsDigit(String s){
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
    public static boolean containsLowerCase(String s){
        for(char c : s.toCharArray()){
            if(Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }
    public static boolean containsUpperCase(String s){
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
    private static java.util.regex.Pattern cleanUrlPattern = java.util.regex.Pattern.compile("[a-zA-Z0-9-]*");
    public static boolean isCleanUrl(String s){
        return cleanUrlPattern.matcher(s).matches();
    }
    public static String secretDigest(String s){
        org.jasypt.util.password.StrongPasswordEncryptor temp = new org.jasypt.util.password.StrongPasswordEncryptor();
        return temp.encryptPassword(s);
    }
    public static boolean secretCheck(String s1,String s2){
        org.jasypt.util.password.StrongPasswordEncryptor temp = new org.jasypt.util.password.StrongPasswordEncryptor();
        return temp.checkPassword(s2,s1);
    }

    //@TODO: there are several issues with primitive types in the generated code, it would be better if they are always boxed
    public static boolean isNullAutoBox(Object o){ return o == null; }

    public static String escapeHtml(String s){
        return org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(s);
    }
    public static String escapeHtml(Object o){ // covers primitive types due to autoboxing
        return org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(o.toString());
    }

    public static String showAttributeEscapeHtml(String s1, Object s2){
        return " " + escapeHtml(s1) + "=\"" + escapeHtml(s2) + "\"";
    }

    // An alternative implementation of FieldInterceptorImpl.readObject / AbstractFieldInterceptor.intercept that supports initializing a single lazy property
    public static Object readLazyProperty(org.webdsl.WebDSLEntity entity, org.hibernate.bytecode.javassist.FieldHandler fieldHandler, String fieldName, Object value) {
    	if(fieldHandler == null) return value;

    	org.hibernate.intercept.javassist.FieldInterceptorImpl fieldInterceptor = (org.hibernate.intercept.javassist.FieldInterceptorImpl)fieldHandler;
    	
    	org.hibernate.engine.SessionImplementor session = fieldInterceptor.getSession();
		if ( session == null ) {
			throw new org.hibernate.LazyInitializationException( "entity with lazy properties is not associated with a session" );
		}
		else if ( !session.isOpen() || !session.isConnected() ) {
			throw new org.hibernate.LazyInitializationException( "session is not connected" );
		}

		final org.hibernate.engine.EntityEntry entry = session.getPersistenceContext().getEntry( entity );
		if ( entry == null ) {
			throw new org.hibernate.HibernateException( "entity is not associated with the session: " + entity.getId() );
		}
		final Object[] snapshot = entry.getLoadedState();
    	org.hibernate.engine.SessionFactoryImplementor factory = session.getFactory();
    	org.hibernate.persister.entity.EntityPersister persister = factory.getEntityPersister(fieldInterceptor.getEntityName());
    	org.hibernate.type.Type type = persister.getPropertyType(fieldName);
		int propertyIndex = persister.getEntityMetamodel().getPropertyIndex(fieldName);

		// Here we initialize the value from the persistence context or from the database
		Object propValue = null;
		try{
			propValue = type.nullSafeGet(null, (String)null, session, entity);
		}
		catch(java.sql.SQLException sqle) {
			throw org.hibernate.exception.JDBCExceptionHelper.convert(
					factory.getSQLExceptionConverter(),
					sqle,
					"could not initialize lazy property: " +
					org.hibernate.pretty.MessageHelper.infoString( persister, entity.getId(), factory ),
					null
				);
		}

		// Here we do the same as AbstractEntityPersister.initializeLazyProperty(String,Object,SessionImplementor,Object[],int,Object)
		persister.setPropertyValue(entity, propertyIndex, propValue, session.getEntityMode());
		if(snapshot != null) {
			snapshot[ propertyIndex ] = type.deepCopy( propValue, session.getEntityMode(), factory );
		}

		return propValue;

		// An earlier implementation
        	/*org.hibernate.type.EntityType type = (org.hibernate.type.EntityType)persister.getPropertyType(fieldName);
    		org.hibernate.engine.EntityUniqueKey euk = new org.hibernate.engine.EntityUniqueKey(
    				type.getAssociatedEntityName(), 
    				type.getRHSUniqueKeyPropertyName(), 
    				entity.getId(),
    				persister.getIdentifierType(),
    				session.getEntityMode(), 
    				factory);
    		Object result = session.getPersistenceContext().getEntity(euk);
    		if(result == null) {
    			persister.getPropertyType(fieldName).nullSafeGet(null, null, session, entity);
    		}
    		else {
    			// here we do the same as AbstractEntityPersister.initializeLazyProperty(String,Object,SessionImplementor,Object[],int,Object)
    			persister.setPropertyValue(entity, ((org.hibernate.persister.entity.AbstractEntityPersister)persister).getPropertyIndex(fieldName), result, session.getEntityMode());

    			fieldInterceptor.getUninitializedFields().remove(fieldName);
    			return result;
    		}*/
    	//return fieldHandler.readObject(entity, fieldName, value);
    }
}