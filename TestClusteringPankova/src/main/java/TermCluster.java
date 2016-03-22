import net.inference.Config;
import net.inference.sqlite.DatabaseApi;
import net.inference.sqlite.dto.TermToTerm;
import net.inference.sqlite.dto.Term;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.AICScore;
import net.sf.javaml.clustering.evaluation.BICScore;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;

import net.sf.javaml.tools.weka.WekaClusterer;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import weka.clusterers.XMeans;
/**
 * Created by M.Pankova on 26.02.16.
 */
public class TermCluster
{
	//public static Connection connection;
	//public static Statement statement;
	//public static ResultSet resSet;

	public static void main(String[] args)throws ClassNotFoundException, SQLException, IOException
	{
		/*Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:Database\\src\\main\\resources\\test.db");
		System.out.println("Database connected");
		statement = connection.createStatement();*/


		DatabaseApi api = new DatabaseApi(Config.Database.TEST, false);

		int countTerm = (int) api.term().count();

		int [][] termsArray = new int[countTerm][countTerm];

		int countTermToTerm = (int) api.termToTerm().count();

		//made matrix between terms
		final List<TermToTerm> termToTermList = api.termToTerm().findAll();
		final List<Term> termList = api.term().findAll();

		/*Term term = termList.get(0);
		FrequencyAnalysis elem = new FrequencyAnalysis(api, term);
		System.out.println(elem.toString());*/


		for(TermToTerm termToTerm: termToTermList)
		{
			//all connection were counted twice!
			termsArray[termToTerm.getFrom().getId()-1][termToTerm.getTo().getId()-1] += termToTerm.getCount();
		}

		int termCount = termList.size();
		int[][] data = new int[termCount][3];

		/*for(int i = 0; i < termsArray.length; i++){
			for(int j = 0; j < termsArray.length; j++)
				System.out.print(termsArray[i][j] + " ");
			System.out.print("\n");
		}*/

		/*for(int i = 0; i < termsArray.length; i++){
			for(int j = 0; j < i; j++){
				if (termsArray[i][j] != 0)
					System.out.println(termsArray[i][j] + " " + termsArray[j][i] + " " +i + " " + j);

			}
		}*/
		FileWriter input = new FileWriter("input.txt", false);

		for(int i = 0; i < termsArray.length; i++){
			for(int j = i + 1; j < termsArray.length; j++){
				if (termsArray[i][j] != 0)
					input.write(i + "\t" + j + "\t" + termsArray[i][j] + "\n");
			}
		}
		input.flush();

		/*Dataset data = new DefaultDataset();
		double[] rel = new double[countTerm];

		for(int i = 0; i < countTerm; i++){
			for(int j = 0; j < countTerm; j++){
				rel[j] = termsArray[i][j];
			}
			Instance tmpInstance = new SparseInstance(rel, termList.get(i).getValue());
			//System.out.println(tmpInstance);
			data.add(tmpInstance);
		}
		System.out.println("Data's size is: " + data.size());
		api.onStop();*/
		//cluster from JavaML

		/*Clusterer km = new KMeans(6,3);
		System.out.println("Start");
		Dataset[] clusters = km.cluster(data);
		System.out.println("Cluster count: " + clusters.length);
		for(int i = 0; i < clusters.length; i++){
			System.out.println("Cluster "+i+": ");
			for(int j = 0; j < clusters[i].size(); j++){
				System.out.print(clusters[i].get(j).classValue()+", ");
				//print count of using in every year for every term
				*//*FrequencyAnalysis elem = new FrequencyAnalysis(api, clusters[i].get(j).classValue().toString() );
				System.out.println(elem.toString());*//*
			}
			System.out.println("");
		}*/


		/*ClusterEvaluation sse= new SumOfSquaredErrors();
		ClusterEvaluation aic = new AICScore();
		ClusterEvaluation bic = new BICScore();

		double score=sse.score(clusters);
		double aicScore = aic.score(clusters);
		double bicScore = bic.score(clusters);

		System.out.println("AIC score: " + aicScore);
		System.out.println("BIC score: " + bicScore);
		System.out.println("Sum of squared errors: " + score);

		//cluster from weka
		XMeans xm = new XMeans();
		Clusterer jmlxm = new WekaClusterer(xm);
		Dataset[] clusters_weka = jmlxm.cluster(data);
		System.out.println("Weka: " + clusters_weka.length + "score is: " + sse.score(clusters_weka));
*/
	}

}
