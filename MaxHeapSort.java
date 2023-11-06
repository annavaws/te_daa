public class MaxHeapSort {

    public static void maxHeapify(int[] A, int i, int size) {
        int largest = i;
        int left = 2*i +1;
        int right = 2*i +2;
        if (left < size && A[left] > A[largest]){
            largest = left;
        }
        if (right < size && A[right] > A[largest]){
            largest = right;
        }
        if (largest != i){
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            maxHeapify(A, largest, size);
        }

    }

    public static void heapSort(int[] A) {
        int size = A.length;
        for (int i = size/2 -1; i>= 0; i--) {
            maxHeapify(A, i, size);
        }
        for (int i = size-1; i >= 0; i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            size--;
            maxHeapify(A, 0, size);
        }
        
    }

  
    
}

