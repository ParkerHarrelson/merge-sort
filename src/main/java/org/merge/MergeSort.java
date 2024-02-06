package org.merge;

public class MergeSort {

    public void sort(int[] A) {
        int p = 0;
        int r = A.length - 1;

        mergeSort(A, p, r);
    }

    private void mergeSort(int[] A, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = (p + r) / 2;
        mergeSort(A, p, q);
        mergeSort(A, q + 1, r);
        merge(A, p, q, r);
    }

    private void merge(int[] A, int p, int q, int r) {
        int sizeLeft = q - p + 1;
        int sizeRight = r - q;

        int[] L = new int[sizeLeft];
        int[] R = new int[sizeRight];

        int i = 0, j = 0;

        for (; i < sizeLeft; i++) {
            L[i] = A[p + i];
        }
        for (; j < sizeRight; j++) {
            R[j] = A[q + j +1];
        }

        i = 0;
        j = 0;
        int k = p;

        while (i < sizeLeft && j < sizeRight) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < sizeLeft) {
            A[k] = L[i];
            i++;
            k++;
        }
        while (j < sizeRight) {
            A[k] = R[j];
            j++;
            k++;
        }
    }
}
