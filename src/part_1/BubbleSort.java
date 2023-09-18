/* ************************************************************************** */
/*                                                                            */
/*                                                 :       ::    :: ::   ::   */
/*   BubbleSort.java                              :+:      :+    :+ :+  :+    */
/*                                               +:+:+     :+    :+ :+  :+    */
/*   By: ykliek <yurii.kliek@auk.edu.ua>        +#   :+    +#    :+ +#:+      */
/*                                             +#+#+#+#+   +#    +# +# +#     */
/*   Created: 2023/09/18 21:51:41 by ykliek   #+       #+  #+    #+ #+  #+    */
/*   Updated: 2023/09/18 21:51:44 by ykliek  ##         ##  ######  ##   ##   */
/*                                                                            */
/* ************************************************************************** */

package part_1;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

/**
 * This class demonstrates bubble sort algorithm.
 */
public class BubbleSort {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		while (true) {
			int length = getUserInt("Enter the length of array: ");
			int choice = getUserInt(
					"Do you want to create your own array or" +
							" generate random one? (1 - own, 2 - random)");
			int isSnapshot = getUserInt(
					"Do you want to create a snapshot of the" +
							" sorting process? (1 - yes, 0 - no)");
			switch (choice) {
				case 1:
					int[] inputArray = inputArray(length);
					System.out.println("Input array: ");
					printArray(inputArray);
					if(isSnapshot == 1) {bubbleSortWithSnapshots(inputArray);}
					int[] sortedArray = bubbleSort(inputArray);
					System.out.println("Sorted array: ");
					printArray(sortedArray);
					break;
				case 2:
					int[] randomArray = createRandomArray(length);
					System.out.println("Random array: ");
					printArray(randomArray);
					if(isSnapshot == 1) {bubbleSortWithSnapshots(randomArray);}
					int[] sortedRandomArray = bubbleSort(randomArray);
					System.out.println("Sorted random array: ");
					printArray(sortedRandomArray);
					break;
				default:
					System.out.println(
							"InputError: Please enter a valid number: ");
					break;
			}
			choice = getUserInt(
					"Do you want to continue? (1 - yes, 2 - no)");
			switch (choice) {
				case 1:
					break;
				case 2:
					System.out.println("Bye!");
					System.exit(0);
					break;
				default:
					System.out.println(
							"InputError: Please enter a valid number: ");
					break;
			}
		}
	}

	/**
	 * This method gets an integer from user.
	 *
	 * @param prompt - message to user
	 * @return - integer
	 */
	private static int getUserInt(String prompt) {
		System.out.println(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("InputError: Please enter a valid number: ");
			scanner.next();
		}
		return scanner.nextInt();
	}


	/**
	 * This method gets an array from user.
	 *
	 * @param length - length of array
	 * @return - array
	 */
	public static int[] inputArray(int length) {
		int[] arr = new int[length];
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < length; i++) {
			System.out.println("Enter the " + i + " element of array: ");
			arr[i] = scanner.nextInt();
		}
		return arr;
	}

	/**
	 * This method creates a random array.
	 *
	 * @param length - length of array
	 * @return - array
	 */
	public static int[] createRandomArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = (int)(Math.random() * 100);
		}
		return arr;
	}

	/**
	 * This method prints an array.
	 *
	 * @param arr - array
	 */
	public static void printArray(int[] arr) {
		for (int value : arr) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	/**
	 * This method sorts an array using bubble sort algorithm.
	 *
	 * @param arr - array
	 * @return - sorted array
	 */
	public static int[] bubbleSort(int[] arr) {
		int length = arr.length;
		for (int i = 0; i < length - 1; i++) {
			// Last i elements are already in place
			for (int j = 0; j < length - i - 1; j++) {
				// Swap if the element found is greater than the next element
				if (arr[j] > arr[j + 1]) {
					// Swap array[j] and array[j + 1]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	/* This method sorts an array using bubble sort algorithm and creates a
	 * snapshot of the sorting process.
	 * __ underlined numbers are not swapped
	 * ** bold numbers are swapped
	 *
	 * @param arr - array
	 * @throws IOException - exception
	 */
	public static void bubbleSortWithSnapshots(int[] arr) throws IOException {
		StringBuilder snapshot = new StringBuilder();
		snapshot.append("Initial array:\n").append(
				toString(arr)).append("\n\n");

		int length = arr.length;
		for (int i = 0; i < length - 1; i++) {
			// Last i elements are already in place
			snapshot.append("Outer loop, iteration ").append(
					i + 1).append(":\n\n");
			snapshot.append(toString(arr)).append("\n\n");
			snapshot.append("Inner loop:\n\n");
			for (int j = 0; j < length - i - 1; j++) {
				// Swap if the element found is greater than the next element
				if (arr[j] > arr[j + 1]) {
					// Swap array[j] and array[j + 1]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					snapshot.append("iteration ").append(j + 1).append(
							":  ").append(toString(
									arr, j, true)).append("\n");
				} else {
					snapshot.append("iteration ").append(j + 1).append(
							":  ").append(toString(
									arr, j, false)).append("\n");
				}
			}
			snapshot.append("\n");
		}

		Files.write(Paths.get(
				"snapshot.txt"), snapshot.toString().getBytes());
	}

	/**
	 * This method creates a string representation of an array.
	 *
	 * @param arr - array
	 * @param highlightIndex - index of highlighted element
	 * @param swapped - is swapped
	 * @return - string representation of an array
	 */
	private static String toString(
			int[] arr, int highlightIndex, boolean swapped) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i == highlightIndex) {
				if (swapped) {
					sb.append("**").append(arr[i]).append(" ").append(
							arr[i + 1]).append("**").append(" ");
				} else {
					sb.append("__").append(arr[i]).append(" ").append(
							arr[i + 1]).append("__").append(" ");
				}
				i++; // skip next element
			} else {
				sb.append(arr[i]).append(" ");
			}
		}
		return sb.toString().trim();
	}

	/**
	 * This method creates a string representation of an array.
	 *
	 * @param arr - array
	 * @return - string representation of an array
	 */
	private static String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int value : arr) {
			sb.append(value).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * This method sorts an array using optimised bubble sort algorithm.
	 *
	 * @param arr - array
	 * @return - sorted array
	 */
	public static int[] bubbleSortOptimised(int[] arr) {
		int length = arr.length;
		boolean swapped; // flag to check if swapping is done

		for (int i = 0; i < length - 1; i++) {
			swapped = false;
			// Last i elements are already in place
			for (int j = 0; j < length - i - 1; j++) {
				// Swap if the element found is greater than the next element
				if (arr[j] > arr[j + 1]) {
					// Swap array[j] and array[j + 1]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
				if (!swapped) {break;}
			}
		}
		return arr;
	}
}
