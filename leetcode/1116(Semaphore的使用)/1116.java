class ZeroEvenOdd {
    private int n;
    private Semaphore s1, s2, s3;
    public ZeroEvenOdd(int n) {
        this.n = n;
        s1 = new Semaphore(1);
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int times = n;
        for(int i = 0; i < times;) {
            s1.acquire();
            printNumber.accept(0);
            s2.release();
            ++i;    

            if(i == times) {
                break;
            }

            s1.acquire();
            printNumber.accept(0);
            s3.release();
            ++i;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int times = n / 2, num = 2;
        for(int i = 0; i < times; ++i) {
            s3.acquire();
            printNumber.accept(num);
            num += 2;
            s1.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int times = n % 2 == 1 ? n / 2 + 1 : n / 2, num = 1;
        for(int i = 0; i < times; ++i) {
            s2.acquire();
            printNumber.accept(num);
            num += 2;
            s1.release();
        }
    }
}