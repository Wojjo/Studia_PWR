/* 
 *  Klasa producent
 *
 *  Autor: Pawel Rogalinski
 *   Data: 1 pazdziernik 2009 r.
 *   Modyfikowa³: Przemys³aw Wojcinowicz 225943
 *   Wersja: 3 koñcowa
 */
class Producent
extends Thread
{
//zmienne	
char stuff = 'A';
Bufor buf;
int number;
boolean stop;
boolean nowWait = false;
//konstruktor
    public Producent(Bufor c, int number) {
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
 	* Metoda okresla dzialanie po uruchomieniu watku Producent
 	*/
    public void run() {
        startBlock : do {
            if (this.stuff > 'Z') {
                this.stuff = 'A';
            }
            char c = this.stuff;
            this.stuff = (char)(c + '\u0001');
            this.buf.put(this.number, c);
            try {
                Producent.sleep((int)(Math.random() * 500.0));
            }
            catch (InterruptedException e) {
                // empty catch block
            }
            do {
                if (!this.stop) continue startBlock;
                try {
                    Producent.sleep(5);
                }
                catch (InterruptedException e) {
                }
            } while (true);
        } while (true);
    }
}

