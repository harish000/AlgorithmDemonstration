import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Radix_sort {

	public static void printArray(int[] list, int size, BufferedWriter bw)
			throws IOException 
	{

		int i;
		bw.write((System.getProperty("line.separator")));
		bw.write("[ ");
		System.out.print("[ ");

		for (i = 0; i < size; i++) {
			System.out.print(list[i] + " ");
			bw.write(list[i] + " ");
		}
		System.out.println("]\n");
		bw.write("]\n");
	}

	public static int findLargestNum(int[] array, int size)
	{

		int i;
		int largestNum = -1;

		for (i = 0; i < size; i++) {
			if (array[i] > largestNum)
				largestNum = array[i];
		}

		return largestNum;
	}

	// Radix Sort
	public static void radixSort(int[] array, int size, BufferedWriter bw)
			throws IOException
	{

		System.out.println("\n\nRunning Radix Sort on Unsorted List!\n\n");
		bw.write((System.getProperty("line.separator")));
		bw.write("\n\nRunning Radix Sort on Unsorted List!\n\n");
		bw.write((System.getProperty("line.separator")));
		// Base 10 is used
		int i;
		int[] semiSorted = new int[size];
		int significantDigit = 1;
		int largestNum = findLargestNum(array, size);
		// Loop until we reach the largest significant digit
		while (largestNum / significantDigit > 0) 
		{
			System.out.println("\n\n\tNow considering " + significantDigit
					+ "'s place for putting into buckets\n");
			bw.write((System.getProperty("line.separator")));
			bw.write((System.getProperty("line.separator")));
			bw.write("\n\n\tNow considering " + significantDigit
					+ "'s place for putting into buckets\n");
			bw.write((System.getProperty("line.separator")));
			System.out.print("\tSorting :" + significantDigit + "'s place ");
			bw.write("\tSorting :" + significantDigit + "'s place ");
			bw.write((System.getProperty("line.separator")));
			printArray(array, size, bw);

			
			int[] bucket = new int[10];
			for (i = 0; i < 10; i++) {
				bucket[i] = 0;
			}

			// Counts the number of "keys" or digits that will go into each
			// bucket
			for (i = 0; i < size; i++)
				bucket[(array[i] / significantDigit) % 10]++;

			/**
			 * Add the count of the previous buckets, Acquires the indexes after
			 * the end of each bucket location in the array Works similar to the
			 * count sort algorithm
			 **/
			for (i = 1; i < 10; i++)
				bucket[i] += bucket[i - 1];

			// Use the bucket to fill a "semiSorted" array
			// for (i = size - 1; i >= 0; i--)
			for (i = 0; i <= size - 1; i++)
			{
				semiSorted[--bucket[(array[i] / significantDigit) % 10]] = array[i];
				int pos = (array[i] / significantDigit) % 10;
				System.out.println("Putting \t " + array[i] + "\t in bucket "
						+ pos);
				bw.write((System.getProperty("line.separator")));
				bw.write("Putting \t " + array[i] + "\t in bucket " + pos
						+ "\n");
				bw.write((System.getProperty("line.separator")));
			}

			
			bw.write((System.getProperty("line.separator")));
			bw.write("Now collect the numbers from the buckets back starting from 0\n\n");
			bw.write((System.getProperty("line.separator")));
			System.out
					.println("Now collect the numbers from the buckets back starting from 0\n");
			bw.write((System.getProperty("line.separator")));
			bw.write("After collecting the numbers now our array looks like");
			bw.write((System.getProperty("line.separator")));
			bw.write("[ ");
			System.out
					.print("After collecting the numbers now our array looks like [ ");
			for (i = 0; i < size; i++) {

				array[i] = semiSorted[i];
				System.out.print(" " + array[i]);
				bw.write(" " + array[i]);
			}
			System.out.println(" ]\n\n");
			bw.write(" ]\n\n\n");
			// Move to next significant digit
			significantDigit *= 10;

		}
	}

	public static void main(String[] args) throws IOException {

		File file = new File("answer.txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		System.out.print("\n\nRunning Radix Sort\n");
		System.out.print("----------------------------------\n");
		bw.write("\n\nRunning Radix Sort\n");
		bw.write((System.getProperty("line.separator")));
		bw.write("----------------------------------------------------------------------\n");
		bw.write((System.getProperty("line.separator")));
		int size = 12;
		int list[]= new int[12];
		Random rnd = new Random(System.currentTimeMillis());
		for (int index = 0; index < 12; index++) {
		    list[index] = rnd.nextInt(900) + 100;
		} 
		
		
		
		//int list[] = { 10, 2, 303, 4021, 293, 1, 0, 429, 480, 92, 2999, 14 };

		System.out.print("\nUnsorted List: ");
		bw.write("\nUnsorted List: ");
		printArray(list, size, bw);
		
		double startTimeProgram = System.currentTimeMillis();
		radixSort(list, size, bw);
		double endTimeProgram = System.currentTimeMillis();
		double time = endTimeProgram- startTimeProgram;
		bw.write((System.getProperty("line.separator")));
		System.out.print("\nSorted List:");
		bw.write((System.getProperty("line.separator")));
		
		bw.write("\nSorted List:");
		printArray(list, size, bw);
		bw.write((System.getProperty("line.separator")));
		System.out.print("\n");

		bw.write((System.getProperty("line.separator")));
		bw.write("Total Running time for the program is "+ time + "ms");
		bw.flush();
		bw.close();

	}
}
