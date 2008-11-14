/*
import org.hibernate._
import org.hibernate.cfg._
import java.util.Properties
import javax.persistence._

object HibernateUtil {
    var sessionFactory : SessionFactory = _
    var p = new Properties
    p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    p.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
    p.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/scalatest?useServerPrepStmts=false");
    p.setProperty("hibernate.connection.username", "root");
    p.setProperty("hibernate.connection.password", "");
    //p.setProperty("hibernate.connection.pool_size", "1");

    p.setProperty("hibernate.current_session_context_class", "thread");
    p.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
    p.setProperty("hibernate.show_sql", "true");
    p.setProperty("hibernate.hbm2ddl.auto", "update");// update / create-drop

    var annotationConfiguration = new AnnotationConfiguration

    //annotationConfiguration.addPackage("datamodel");
    annotationConfiguration.addAnnotatedClass(classOf[User]);

    annotationConfiguration.addProperties(p);
    sessionFactory = annotationConfiguration.buildSessionFactory();
}


@Entity
class User extends  java.io.Serializable {
  @Id @GeneratedValue 
  var id: Int = _
  var name : String = _
}

object TestHibernate {
  def main(args : Array[String]) {
    var hibSession = HibernateUtil.sessionFactory.getCurrentSession
	hibSession.beginTransaction
	println("Connection made")
    /*val u = new User
    print("Name: ")
	u.name = readLine.trim
	println("Saving " + u.name)
	hibSession.save(u)
 */
	println("Selecting all users")
	val it = hibSession.createQuery("from User").list.iterator
	while(it.hasNext) {
	  val user = it.next.asInstanceOf[User]
	  println(user.name)
	}
	hibSession.getTransaction.commit
	println("Done!")
   }
}
*/