public class Spoiler {
        // static instance
        private static Spoiler instance = new Spoiler();
        // port
        public Port port;
        private String manufacturer = "<3106335> / <4669114>";
        private int degree = 0;

        // private constructor
        private Spoiler() {
            port = new Port();
        }

        // static method getInstance
        public static Spoiler getInstance() {
            return instance;
        }

        // inner methods
        public String innerVersion() {
            return "Spoiler // " + manufacturer;
        }

        public int innerNeutral() {
            degree = 0;
            return degree;
        }

        public int innerFullUp() {
            degree = 20;
            return degree;
        }

        public int innerDown(int degree) {
            this.degree -= degree;
            if(this.degree < 0) this.degree = 0;
            return this.degree;
        }

        public int innerUp(int degree) {
            this.degree += degree;
            if(this.degree > 20) this.degree = 20;
            return this.degree;
        }

        // inner class port
        public class Port implements ISpoiler {
            public String version() {return innerVersion();}

            public int neutral() {return innerNeutral();}

            public int fullUp() {return innerFullUp();}

            public int up(int degree) {return innerUp(degree);}

            public int down(int degree) {return innerDown(degree);}
        }
}
