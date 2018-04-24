using AForge.Imaging.Filters;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WIA;

namespace Scanner
{
    public partial class Form1 : Form
    {
        string path;
        int bright;
        int resolution;

        public Form1()
        {
            InitializeComponent();
            bright = 0;
            for (int i = 1; i < 5; i++)
            {
                comboBox2.Items.Add(i * 150);
            }
            comboBox2.SelectedIndex = 0;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            comboBox1.SelectedIndex = -1;
            ListScanners();

            // Set JPEG as default
            comboBox1.SelectedIndex = 1;
        }

        private void ListScanners()
        {
            // Clear the ListBox.
            comboBox3.Items.Clear();

            // Create a DeviceManager instance
            var deviceManager = new DeviceManager();

            // Loop through the list of devices and add the name to the listbox
            for (int i = 1; i <= deviceManager.DeviceInfos.Count; i++)
            {
                // Add the device only if it's a scanner
                if (deviceManager.DeviceInfos[i].Type != WiaDeviceType.ScannerDeviceType)
                {
                    continue;
                }

                // Add the Scanner device to the listbox (the entire DeviceInfos object)
                // Important: we store an object of type scanner (which ToString method returns the name of the scanner)
                comboBox3.Items.Add(new Scanner(deviceManager.DeviceInfos[i]));
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            resolution = Int32.Parse(comboBox2.SelectedItem.ToString());
            Task.Factory.StartNew(StartScanning);
            trackBar1.Enabled = true;
        }

        public void StartScanning()
        {
            Scanner device = null;

            this.Invoke(new MethodInvoker(delegate () { device = comboBox3.SelectedItem as Scanner; }));

            if (device == null)
            {
                MessageBox.Show("Nie wybrano skanera!", "Ostrzezenie", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            else if (String.IsNullOrEmpty(textBox2.Text))
            {
                MessageBox.Show("Wprowadz nazwe pliku!", "Ostrzezenie", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            ImageFile image = new ImageFile();
            string imageExtension = "";
            device.SetResolution(resolution);
            this.Invoke(new MethodInvoker(delegate ()
            {
                switch (comboBox1.SelectedIndex)
                {
                    case 0:
                        image = device.ScanPNG();
                        imageExtension = ".png";
                        break;
                    case 1:
                        image = device.ScanJPEG();
                        imageExtension = ".jpeg";
                        break;
                    case 2:
                        image = device.ScanTIFF();
                        imageExtension = ".tiff";
                        break;
                }
            }));

            // Save the image
            path = Path.Combine(textBox2.Text + imageExtension);

            if (File.Exists(path))
            {
                File.Delete(path);
            }

            image.SaveFile(path);

            pictureBox1.Image = new Bitmap(path);
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            label7.Text = trackBar1.Value.ToString();

            Bitmap image = new Bitmap(pictureBox1.Image);
            BrightnessCorrection filter = new BrightnessCorrection(trackBar1.Value - bright);
            filter.ApplyInPlace(image);
            pictureBox1.Image = image;

            image.Save(textBox2.Text, ImageFormat.Jpeg);
            bright = trackBar1.Value;
        }

        private void comboBox3_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox3.SelectedIndex != -1)
            {
                button1.Enabled = true;
            }
        }
    }
}
