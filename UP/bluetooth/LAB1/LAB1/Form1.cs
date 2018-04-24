using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using InTheHand.Net;
using InTheHand.Net.Bluetooth;
using InTheHand.Net.Sockets;
using System.Threading;

namespace LAB1
{
    public partial class Form1 : Form
    {

        public const string DEVICE_PIN = "1234";
        private bool continueScanning = false;
        private bool czySparowano = false;
        /* Lista znalezionych urzadzen */
        private List<BluetoothDeviceInfo> clients = new List<BluetoothDeviceInfo>();
        BluetoothDeviceInfo deviceToPair = null;

        private void SendFiles()
        {
            // dla kazdej sciezki na liscie probujemy wyslac odpowiadajacy jej plik
            foreach (string line in listBox3.Items)
            {
                //@"d:\"+FILE_NAME
                try
                {
                    if (deviceToPair != null)
                        SendFile(deviceToPair, line);
                }
                catch (Exception e)
                {
                    MessageBox.Show("BlAD PODCZAS WYSYLANIA:" + Environment.NewLine + e.Message);
                }
            }

        }
        private void SendFile(BluetoothDeviceInfo device, string destinationPath)
        {

            textBox1.Text += ("Rozpoczynamy wysylanie...");
            // Build te OBEX uri and create an OBEX web request
            var uri = new Uri("obex://" + device.DeviceAddress + "/" + destinationPath);
            var request = new ObexWebRequest(uri);
            request.ReadFile(destinationPath);
            var response = (ObexWebResponse)request.GetResponse();
            textBox1.Text += (response.StatusCode.ToString());
            // check response.StatusCode
            textBox1.Text += ("Wysylanie zakonczone.");

        }
        private void ReceiveFile()
        {
            textBox1.Text += ("Rozpoczynam nasluchiwanie na nowe wiadomosci...") + Environment.NewLine;

            // wykonuje do momenu gdy nie zostaniemy rozlaczeni z urzadzeniem
            while (czySparowano)
            {
                var listener = new ObexListener(ObexTransport.Bluetooth);
                listener.Start();
                ObexListenerContext ctx = listener.GetContext();
                ObexListenerRequest req = ctx.Request;
                String[] pathSplits = req.RawUrl.Split('/');
                String filename = pathSplits[pathSplits.Length - 1];
                req.WriteFile(filename);
                textBox1.Text += (filename);
                listener.Stop();
            }
        }
        private void PairWithClient()
        {

            //sprawdzamy ktory wybrany z list boxa
            foreach (var client in clients)
                if (client.DeviceName == (string)listBox1.SelectedItem)
                    deviceToPair = client;

            if (deviceToPair != null)
            {
                textBox1.Text += ("Urzadzenie, z ktorym sie parujemy:") + Environment.NewLine;
                var blueToothInfo =
                string.Format(
                "- DeviceName: {0}{1}  Connected: {2}{1}  Address: {3}{1}  Last seen: {4}{1}  Last used: {5}{1}",
                deviceToPair.DeviceName, Environment.NewLine, deviceToPair.Connected, deviceToPair.DeviceAddress, deviceToPair.LastSeen,
                deviceToPair.LastUsed);
                blueToothInfo += string.Format("  Class of device{0}   Device: {1}{0}   Major Device: {2}{0}   Service: {3}",
                    Environment.NewLine, deviceToPair.ClassOfDevice.Device, deviceToPair.ClassOfDevice.MajorDevice, deviceToPair.ClassOfDevice.Service);
                textBox1.Text += (blueToothInfo) + Environment.NewLine;

                deviceToPair.Update();
                deviceToPair.Refresh();
                deviceToPair.SetServiceState(BluetoothService.ObexObjectPush, true);
                bool isPaired = BluetoothSecurity.PairRequest(deviceToPair.DeviceAddress, DEVICE_PIN);
                if (isPaired)
                {
                    textBox1.Text += ("Urzadzenie sparowane!") + Environment.NewLine;
                    listBox2.Items.Add(deviceToPair.DeviceName);

                    czySparowano = true;

                    // jezeli sparowano to aktywujemy tryb nasluchu plikow

                    Thread thread = new Thread(ReceiveFile);
                    thread.Priority = ThreadPriority.Normal;
                    thread.IsBackground = true;
                    thread.Start();
                }
                else
                {
                    textBox1.Text += ("Urzadzenie niesparowane!") + Environment.NewLine;
                }
            }
            else
            {
                MessageBox.Show("Nie wybrano urzadzenia do sparowania!");
            }
            button2.Enabled = true;
        }
        private void ScanDriver()
        {
            textBox1.Text += ("Wyszukuje urzadzen...") + Environment.NewLine;
            try
            {
                while (continueScanning)
                {
                    /* linie odpowiedzialne za wyszukanie dostepnych adapterow, polaczenie z adapterem,
                       oraz wyszukanie urzadzzen w poblizu. Wykonywanie metody discoverDevices moze zajac
                       nawet 20 sekund 
                       */
                    var bluetoothClient = new BluetoothClient();
                    var devices = bluetoothClient.DiscoverDevices();

                    // dla kazdego uzadzenia wyswietl informacje o nim
                    foreach (var device in devices)
                    {
                        var blueToothInfo =
                        string.Format(
                        "- DeviceName: {0}{1}  Connected: {2}{1}  Address: {3}{1}  Last seen: {4}{1}  Last used: {5}{1}",
                        device.DeviceName, Environment.NewLine, device.Connected, device.DeviceAddress, device.LastSeen,
                        device.LastUsed);
                        blueToothInfo += string.Format("  Class of device{0}   Device: {1}{0}   Major Device: {2}{0}   Service: {3}",
                            Environment.NewLine, device.ClassOfDevice.Device, device.ClassOfDevice.MajorDevice, device.ClassOfDevice.Service);
                        textBox1.Text += blueToothInfo + Environment.NewLine;

                        bool isAlreadyOnList = false;

                        // wyszukaj czy jest juz na liscie znalezionych uzadzen
                        if (device.DeviceName != null)
                            foreach (string line in listBox1.Items)
                                if (line == device.DeviceName)
                                    isAlreadyOnList = true;

                        // jezeli nie ma to dodaj
                        if (!isAlreadyOnList)
                        {
                            listBox1.Items.Add(device.DeviceName);
                            clients.Add(device);
                        }
                    }

                }
                textBox1.Text += ("Zatrzymuje wyszukiwanie...") + Environment.NewLine;
                // koniec watku
            }
            catch (Exception e)
            {
                MessageBox.Show("BLAD!" + Environment.NewLine + e.Message);
            }

        }
        private void UnPair()
        {
            // flaga czy rozparowano
            bool isUnPaired = false;

            // do przechowania zaznaczonego itemu z listy
            string temp = "";

            // znajdz na liscie 2
            listBox2.Refresh();

            try
            {
                foreach (string line in listBox2.Items)
                    if (line == (string)listBox2.SelectedItem)
                        temp = line;
            }
            catch (Exception)
            { }

            // dla znalej nazwy znajdz odpowiadajacego klienta na liscie i jezeli jest to usun go z listy
            foreach (var client in clients)
            {
                if (client.DeviceName == temp)
                    isUnPaired = BluetoothSecurity.RemoveDevice(client.DeviceAddress);
                if (isUnPaired)
                {
                    textBox1.Text += ("Urzadzenie rozlaczone!") + Environment.NewLine;
                    listBox2.Items.Remove(client.DeviceName);

                    //zmien flage
                    czySparowano = false;
                }
                else
                {
                    textBox1.Text += ("Urzadzenie nierozlaczone!") + Environment.NewLine;
                }
            }
        }

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // nie sprawdzaj nielegalnego uzycia komponentow textbox
            // potrzebne aby uzywac textbox wielowatkowo

            TextBox.CheckForIllegalCrossThreadCalls = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Thread thread = new Thread(ScanDriver);
            thread.Priority = ThreadPriority.Highest;
            thread.IsBackground = true;
            thread.Start();

            // po wystartowaniu watka zadbaj o odpowiednia zmiane nazwy przyciusku i zmiane jego funkcji
            if (button1.Text == "Skanuj urządzenia")
            {
                button1.Text = "Zatrzymaj skanowanie"; continueScanning = true;
            }
            else if (button1.Text == "Zatrzymaj skanowanie")
            {
                button1.Text = "Skanuj urządzenia"; continueScanning = false;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            // przekaz referencje do metody i wystartuj watek
            Thread thread = new Thread(PairWithClient);
            thread.Priority = ThreadPriority.Highest;
            thread.IsBackground = true;
            thread.Start();
            button2.Enabled = false;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            UnPair();
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
            // dodaj do listy sciezki do wszystkich wybranych plikow w oknie dialogowym
            foreach (var fileName in openFileDialog1.FileNames)
                listBox3.Items.Add(fileName);
        }

        private void otwórzToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            //wystartuj nowy watek przekazujac referencje do funkcji
            Thread thread = new Thread(SendFiles);
            thread.Priority = ThreadPriority.Highest;
            thread.IsBackground = true;
            thread.Start();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            // dla kazdego zaznaczonego na liscie usun go
            for (int n = listBox3.SelectedItems.Count; n > 0; n--)
            {
                listBox3.Items.Remove(listBox3.SelectedItem);
            }
        }

        private void pomocToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Opis programu:" + Environment.NewLine +
                "Aby wyszukać urządzenia wciśnij przycisk [Skanuj urządzenia]" + Environment.NewLine +
                "Aby sparować z urządzeniem wybierz je z listy znalezionych urządzeń klikając myszką." + Environment.NewLine +
                "Aby wysłać plik do urządzenia wybierz je z listy spraowanych urządzeń," + Environment.NewLine +
                "a następnie wybierz pliki, które chcesz przesłać i kliknij wyślij pliki.", "OKNO POMOCY", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }
}