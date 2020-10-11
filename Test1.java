public class Test1 {
    public static void main(String[] args) {
        // Работаем здесь
        int[] intArray = new int[] {6, 5, 8, 3, 9, 1, 7, 4, 2, 0,};
        System.out.println("Длина массива - " + intArray.length);


        int n = 0;
        System.out.print("Массив содержит ");
        for (int item : intArray) {
            System.out.print(intArray[n] + " ");
            n++;
        }


        System.out.println();
        System.out.print("После сортировки ");
        for (int i = 0; i < intArray.length-1; i++) {
            for (int j = 0; j < intArray.length - i - 1; j++) {
                if (intArray[j] > intArray[j + 1]) {
                    int temp = intArray[j];
                    intArray[j] = intArray[j + 1];
                    intArray[j + 1] = temp;
                }
            }
        }


        n = 0;
        for (int item : intArray) {
            System.out.print(intArray[n] + " ");
            n++;
        }


        System.out.println();
        if (intArray.length % 2 == 0){
            int sr = intArray.length / 2;
            double median = (intArray[sr] + intArray[sr-1]);
            System.out.println("Медиана отсортированного массива " + median / 2);
        }else{
            System.out.println("Нахождение медианы невозможно");
        }


    }
}
