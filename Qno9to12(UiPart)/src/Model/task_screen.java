package Model;

public class task_screen {
        private int jobId;
        private String task1;
        private String task2;

        public task_screen(int jobId, String task1, String task2) {
            this.jobId = jobId;
            this.task1 = task1;
            this.task2 = task2;
        }


    public int getJobId() {
            return jobId;
        }

        public void setJobId(int jobId) {
            this.jobId = jobId;
        }

        public String getTask1() {
            return task1;
        }

        public void setTask1(String task1) {
            this.task1 = task1;
        }

        public String getTask2() {
            return task2;
        }

        public void setTask2(String task2) {
            this.task2 = task2;
        }


}
