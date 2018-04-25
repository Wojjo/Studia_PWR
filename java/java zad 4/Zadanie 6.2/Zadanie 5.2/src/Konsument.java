/* 
 *  Klasa Konsument
 *
 *  Autor: Pawel Rogalinski
 *  Data: 1 pazdziernik 2009 r.
 *  Modyfikowa³: Przemys³aw Wojcinowicz 225943
 *  Wersja: 3 koñcowa
 */
class Konsument
extends Thread 
{
//zmienne
Bufor buf;
int number;
boolean stop;
boolean nowWait = false;


  //konstruktor 
    public Konsument(Bufor c, int number)
    {
        this.buf = c;
        this.number = number;
    }
    
    public void play() 
    {
        this.stop = false;
    }
    //stop
    public void pause() 
    {
        this.stop = true;
    }

    /*
 	* Metoda okresla dzialanie po uruchomieniu watku Konsument
 	*/
    public void run() {
        block4 : do {
            this.buf.synch(this.number);
            try {
                Konsument.sleep((int)(Math.random() * 500));
            }
            catch (InterruptedException e) {
                // empty catch block
            }
            do {
                if (!this.stop) continue block4;
                try {
                    Konsument.sleep(5);
                }
                catch (InterruptedException e) {
                }
            } while (true);
        } while (true);
    }
}

