package sortingvisualiser;

public class SortingAlgorithms {

    public static void bubbleSort(SortingPanel panel, int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1 && panel.isRunning(); i++) {
            for (int j = 0; j < n - i - 1 && panel.isRunning(); j++) {
                panel.highlight(j, j + 1);
                panel.repaintAndSleep();
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            panel.markSorted(n - i - 1);
        }
        for (int i = 0; i < n; i++) panel.markSorted(i);
    }

    public static void selectionSort(SortingPanel panel, int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1 && panel.isRunning(); i++) {
            int min = i;
            for (int j = i + 1; j < n && panel.isRunning(); j++) {
                panel.highlight(min, j);
                panel.repaintAndSleep();
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
            panel.markSorted(i);
        }
        for (int i = 0; i < n; i++) panel.markSorted(i);
    }

    public static void insertionSort(SortingPanel panel, int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n && panel.isRunning(); i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key && panel.isRunning()) {
                panel.highlight(j, j + 1);
                arr[j + 1] = arr[j];
                j--;
                panel.repaintAndSleep();
            }
            arr[j + 1] = key;
            panel.markSorted(i);
        }
        for (int i = 0; i < n; i++) panel.markSorted(i);
    }

    public static void mergeSort(SortingPanel panel, int[] arr, int l, int r) {
        if (l < r && panel.isRunning()) {
            int m = (l + r) / 2;
            mergeSort(panel, arr, l, m);
            mergeSort(panel, arr, m + 1, r);
            merge(panel, arr, l, m, r);
        }
    }

    private static void merge(SortingPanel panel, int[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2 && panel.isRunning()) {
            panel.highlight(l + i, m + 1 + j);
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
            panel.repaintAndSleep();
        }
        while (i < n1 && panel.isRunning()) arr[k++] = L[i++];
        while (j < n2 && panel.isRunning()) arr[k++] = R[j++];
        for (int idx = l; idx <= r; idx++) panel.markSorted(idx);
    }
public static void quickSort(SortingPanel panel, int[] arr, int low, int high) {
    if (low < high && panel.isRunning()) {
        int pi = partition(panel, arr, low, high); // partition and get pivot index
        quickSort(panel, arr, low, pi - 1);        // sort left
        quickSort(panel, arr, pi + 1, high);       // sort right
        panel.markSorted(pi);                       // mark pivot as sorted after recursion
    }
    // Optional: if top-level recursion finishes, mark all as sorted
    if (low == 0 && high == arr.length - 1) {
        for (int i = 0; i < arr.length; i++) panel.markSorted(i);
    }
}

private static int partition(SortingPanel panel, int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high && panel.isRunning(); j++) {
        panel.highlight(j, high);  // Highlight current vs pivot
        panel.repaintAndSleep();
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            panel.repaintAndSleep();
        }
    }
    // Swap pivot into correct position
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    panel.repaintAndSleep();
    return i + 1; // return pivot index
}
}
