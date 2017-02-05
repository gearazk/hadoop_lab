import
import
import
import
import
import
import
import
org.apache.hadoop.conf.Configuration;
org.apache.hadoop.fs.Path;
org.apache.hadoop.io.IntWritable;
org.apache.hadoop.io.Text;
org.apache.hadoop.mapreduce.Job;
org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
org.apache.hadoop.util.GenericOptionsParser;
public class MaxMonthTemp {
public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
String[] programArgs =
new GenericOptionsParser(conf, args).getRemainingArgs();
if (programArgs.length != 2) {
System.err.println("Usage: MaxTemp <in> <out>");
System.exit(2);
}
Job job = Job.getInstance(conf, "Monthly Max Temp");
job.setJarByClass(MaxMonthTemp.class);
job.setMapperClass(MaxTempMapper.class);
job.setReducerClass(MaxTempReducer.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(IntWritable.class);
FileInputFormat.addInputPath(job, new Path(programArgs[0]));
FileOutputFormat.setOutputPath(job, new Path(programArgs[1]));
// Submit the job and wait for it to finish.
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}