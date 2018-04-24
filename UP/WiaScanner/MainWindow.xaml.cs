using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using WIA;
using WIAScanner_WPFApp.ScanerServices;

namespace WIAScanner_WPFApp
{
    /// <summary>
    /// Logika interakcji dla klasy MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<String> bitDepths = new List<string>();
        public List<String> BitDepths
        {
            get { return bitDepths; }
            set { bitDepths = value; }
        }

        private List<String> resolution = new List<string>();
        public List<String> Resolution
        {
            get { return resolution; }
            set { resolution = value; }
        }

        private BitmapSource scannedImage;
        public BitmapSource ScannedImage
        {
            get { return scannedImage; }
            set { scannedImage = value; }
        }

        public MainWindow()
        {
            InitializeComponent();
            DataContext = this;

            SetSettings();
        }

        void SetSettings()
        {
            bitDepths.Add("Color");
            bitDepths.Add("Grayscale");
            bitDepths.Add("Black & White");

            resolution.Add("100 dpi");
            resolution.Add("150 dpi");
            resolution.Add("200 dpi");
            resolution.Add("300 dpi");

            BitDepthComboBox.SelectedIndex = 0;
  
        }

        int setBitDepths()
        {
            int value = 0;
            if(BitDepthComboBox.SelectedValuePath == "Color")
            {
                value = 1;
            }
            else if(BitDepthComboBox.SelectedValuePath == "Grayscale")
            {
                value = 2;
            }
            return value;
        }
        public void Scan()
        {
            var scanner = new ScannerService();
            scanner.NativeUI = (bool)NativeUICeckBox.IsChecked;
            scanner.BitDepth = setBitDepths();

            try
            {
                //WiaImageIntent.ColorIntent, NativeUICeckBox.IsChecked
                ImageFile file = scanner.Scan();

                if (file != null)
                {
                    var converter = new ScannerImageConverter();

                    //ScannedImage = converter.ConvertScannedImage(file);
                    ScannedImage = converter.InMemoryConvertScannedImage(file);
                }
                else
                {
                    ScannedImage = null;
                }

            }
            catch (ScannerException ex)
            {
                MessageBox.Show(ex.Message, "Problem z skanowaniem obrazu!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Błąd!");
            }
        }

        private void ScanButton_Click(object sender, RoutedEventArgs e)
        {
            Scan();
            ScannerImage.Source = ScannedImage;
        }

        private void BitDepthComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }
    }


}
