import java.beans.Statement;
import java.sql.Driver;

import org.neo4j.driver.internal.messaging.RecordMessage;
import org.neo4j.driver.internal.spi.Connection;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;

public class wiki {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		org.neo4j.driver.v1.Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "changeme"));
		
		Session session = driver.session();
		session.run("Create (ee:Page {title: \"Tree\", id: 1}),\n" + 
				"(cc:Page {title: \"Bike\", id:2}),\n" + 
				"(ff:Page {title: \"Wheel\", id:3}),\n" + 
				"(ee)-[:CONNECTS]->(cc),\n" + 
				"(cc)-[:CONNECTS]->(ff)");
		StatementResult response = session.run("MATCH p=()-[r:CONNECTS]->() RETURN p LIMIT 25");
		
		while(response.hasNext()) {
			System.out.println(response.next());
		}
		
		driver.close();
	}

}
