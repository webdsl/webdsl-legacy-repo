package nativejava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.IOUtils;

import com.example.engine.domain.ApplicationVersion;

public class ScheduleBuild {
	public static boolean scheduleBuild(String outPath, ApplicationVersion version) {
		try {
			InputStream in = version.getZip().getContentStream();
			String appName = version.getApplication().getName();
			String versionId = version.getId().toString();
			File dir = new File(outPath+"/"+appName+"/"+versionId);
			dir.mkdirs();
			FileOutputStream out = new FileOutputStream(outPath+"/"+appName+"/"+versionId+".zip");
			IOUtils.copy(in, out);
			out.close();
			in.close();
			
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		    Connection connection = connectionFactory.createConnection();
		    connection.start();
		    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    Queue q = session.createQueue("engine-build");
		      
		    MessageProducer producer = session.createProducer(q);
		    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		    producer.send(session.createTextMessage(outPath+"/"+appName+"/"+versionId+".zip"));
		    connection.stop();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
