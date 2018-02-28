package curtis.max.dbt330;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4J_Database_Connection {
	// private String databaseConnectionStrring;
	//
	// public String getDatabaseConnectionStrring() {
	// return databaseConnectionStrring;
	// }
	//
	// public void setDatabaseConnectionStrring(String databaseConnectionStrring) {
	// this.databaseConnectionStrring = databaseConnectionStrring;
	// }
	//
	// public void create_Node(String title, String page_id) {
	// String comand = "";
	// comand += "create (p:page {title:\"" + title + "\", id:" + page_id + "})";
	// System.out.println(comand);
	// }
	//
	// public void match(String from_Id, String to_Title) {
	// String command = "match(fp:page) where fp.pageid=" + from_Id;
	// command += " match(tp:page) where tp.title =\"" + to_Title + "\"";
	// command += " create(fp)-[:CONNECTS]->(tp)";
	// System.out.println(command);
	// }

	public static void main(String[] args) {
		GraphDatabaseService t = new GraphDatabaseFactory().newEmbeddedDatabase(null);
		// Neo4J_Database_Connection m = new Neo4J_Database_Connection();
		Node first;
		Node sec;
		Relationship relationship;
		first = t.createNode();
		first.setProperty("temp1", "test1");
		sec = t.createNode();
		sec.setProperty("temp2", "test2");
		relationship = first.createRelationshipTo(sec, RelTypes.KNOWS);
		relationship.setProperty("RELMSG", "HELOOW");
		System.out.println(first.getProperty("temp1"));
		System.out.println(relationship.getProperty("RELMSG"));

	}
}
