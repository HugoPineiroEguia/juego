package juego;

import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {

        int puntos = -1;
        Jugador jugador = new Jugador();
        jugador.setHp(50);
        jugador.setName();

        while (jugador.getBatalla() == false){

            puntos += 1;
            jugador.setCombate(false);

            Monstruo monstruo = new Monstruo();
            monstruo.setHp((int) (Math.random() * (60 - 30) + 30));
            System.out.println("\n--HA APARECIDO UN NUEVO BICHO PUTO--\n");

            jugador.getHp_general(jugador, monstruo);

            jugador.combate(jugador, monstruo);

            if (jugador.getHp()<=0){

                System.out.println("\n-TE HAS QUEDADO SIN VIDA, Hp: 0-");
                System.out.println("-ACABASTE EL JUEGO CON: " + puntos + " BICHOS DERROTADOS\n\n");

                jugador.setBatalla(true);
            }
            else{
                jugador.setBatalla(false);
            }

        }

    }


    public static class Jugador {

        private int hp;
        private int ad;
        private int esquivasion;

        Scanner sc = new Scanner(System.in);
        private int opcion;
        private boolean combate = false;
        private boolean batalla = false;

        public Jugador() {

        }

        public void setHp(int hp) {

            this.hp = hp;

        }

        public int getHp() {

            return hp;

        }

        public void setAd(int ad) {

            this.ad = ad;

        }

        public int getAd() {

            return ad;

        }

        public void setName() {

            System.out.print("NOMBRE: ");
            String name = sc.nextLine();
            System.out.println("\n\nHOLA " + name + ".\n");

        }

        public void getHp_general(Jugador jugador, Monstruo monstruo) {

            System.out.println("-VIDA JUGADOR: " + jugador.getHp() + "-");
            System.out.println("-VIDA MONSTRUO: " + monstruo.getHp() + "-");

        }

        public void atacar(Jugador jugador, Monstruo monstruo) {

            //Valor de ataque random
            jugador.setAd((int) (Math.random() * (18 - 13) + 13));
            monstruo.setAd((int) (Math.random() * (21 - 17) + 17));

            //Golpe del jugador al monstruo
            monstruo.setHp(monstruo.getHp() - jugador.getAd());

            if (monstruo.getHp() > 0) {

                //Golpe del monstruo al jugador
                jugador.setHp(jugador.getHp() - monstruo.getAd());

                System.out.println("\n-LE ENGANCHAS UN DUYUNOUDAWE DE: " + jugador.getAd()
                        + "-\n-EL MONSTRUO TE HA ATACADO, TE HA DADO UNA YOYA DE: " + monstruo.getAd() + "-\n");
            }
            if (monstruo.getHp() <= 0) {

                monstruo.setHp(0);
                System.out.println("\n-EL HOSTIAS RECIBE UN LATIFUNDIO DE: " + jugador.getAd() + "+");
                System.out.println("-EL BICHO MAMO-\n");

            }

            if (jugador.getHp()<=0){

                jugador.setHp(0);
                jugador.setCombate(true);

            }

            jugador.getHp_general(jugador, monstruo);

        }

        public void esquivar(Jugador jugador, Monstruo monstruo) {

            jugador.setAd((int) (Math.random() * (18 - 13) + 13));
            monstruo.setAd((int) (Math.random() * (28 - 17) + 17));
            esquivasion = (int) (Math.random() * 4);

            if (esquivasion > 2) {
                monstruo.setHp(monstruo.getHp() - jugador.getAd());
                System.out.println("\n-ESQUIVASTE EL GOLPE DEL ENEMIGO SIN PROBLEMA, DEVUELVES UN GOLPE DE:" + jugador.getAd() + " DE DANHO-\n");
            }

            else {
                System.out.println("\n-NO LOGRASTE ESQUIVAR A TIEMPO, RECIBES " + monstruo.getAd() + " DE DANHO-\n");
                jugador.setHp(jugador.getHp() - monstruo.getAd());
            }



            if (monstruo.getHp() <= 0) {

                monstruo.setHp(0);
                System.out.println("-EL HOSTIAS RECIBE UN LATIFUNDIO DE: " + jugador.getAd() + "-");
                System.out.println("-EL BICHO MAMO-\n");

            }

            if (jugador.getHp()<=0){

                jugador.setHp(0);
                jugador.setCombate(true);
            }


            jugador.getHp_general(jugador, monstruo);



        }

        public void curar(Jugador jugador, Monstruo monstruo){

            monstruo.setAd((int) (Math.random() * (25 - 17) + 17));

            jugador.setHp(jugador.getHp()+ 20);
            System.out.println("\n-TU SALUD HA AUMENTADO 20 PUNTOS-");
            jugador.setHp(jugador.getHp() - monstruo.getAd());
            System.out.println("-EL MONSTRUO TE HA ATACADO, TE HA DADO UNA YOYA DE: " + monstruo.getAd() + "-\n");

            jugador.getHp_general(jugador, monstruo);

        }

        public boolean getBatalla(){

            return batalla;

        }

        public void setBatalla (boolean batalla){

            this.batalla = batalla;

        }

        public void setCombate (boolean combate) {

            this.combate = combate;

        }

        public boolean getCombate (){

            return combate;

        }

        public void combate(Jugador jugador, Monstruo monstruo){

            while (combate == false)  {

                System.out.print("-------------------"
                        + "\n1.ATACAR\n2.ESQUIVAR\n3.CURAR\n-------------------\n¿QUE QUIERES HACER?: ");
                opcion = sc.nextInt(); System.out.println("-------------------");

                switch (opcion) {

                    case 1:
                        System.out.println("\n--INICIO DEL ATAQUE--");
                        jugador.atacar(jugador, monstruo);
                        System.out.println("\n--FIN DEL ATAQUE--\n");

                        if (monstruo.getHp()<=0){combate=true;}

                        break;

                    case 2:
                        System.out.println("\n--ESQUIVANDO--");
                        jugador.esquivar(jugador, monstruo);
                        System.out.println("\n--ESQUIVE--");

                        if (monstruo.getHp()<=0){combate=true;}

                        break;

                    case 3:
                        System.out.println("\n--INICIO CURASION--");
                        jugador.curar(jugador, monstruo);
                        System.out.println("\n--FIN CURASION--\n");
                }
            }
        }

    }

    public static class Monstruo {

        int hp;
        int ad;

        public Monstruo(){

        }

        public void setHp (int hp){

            this.hp = hp;

        }

        public int getHp(){

            return hp;

        }

        public void setAd (int ad){

            this.ad = ad;

        }

        public int getAd(){

            return ad;

        }


    }
}
