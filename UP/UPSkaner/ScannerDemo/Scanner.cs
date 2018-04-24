using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using WIA;
using System.Windows.Forms;

namespace Scanner
{
    class Scanner
    {
        private readonly DeviceInfo _deviceInfo;
        private int resolution = 150;       // rozdzielczosc domyslna, bedzie domyslnie ustawiona po wlaczeniu aplikacji
        private int width_pixel = 1250;     // domyslnie ustawione parametry skanera
        private int height_pixel = 1700;
        private int color_mode = 1;

        public Scanner(DeviceInfo deviceInfo)
        {
            this._deviceInfo = deviceInfo;      // parametr z informajca o nazwie skanera
        }

        public void SetResolution(int resolution)
        {
            width_pixel = 1250 * (resolution / 150);
            height_pixel = 1700 * (resolution / 150);
            this.resolution = resolution;
        }
 
        /// <summary>
        /// Skanowanie obrazu w formacie PNG
        /// </summary>
        /// <returns></returns>
        public ImageFile ScanPNG()
        {
            // Wyswietlenie dostepnych urzadzen,
            // nastepnie wybieramy urzadzenie,
            // laczymy sie z urzadzeniem, mamy mozliwosc wykonania skanu
            var device = this._deviceInfo.Connect();
            CommonDialogClass dlg = new CommonDialogClass(); 
            var item = device.Items[1];
            try
            {
                AdjustScannerSettings(item, resolution, 0, 0, width_pixel, height_pixel, 0, 0, color_mode);  // ustawienie skanera

                object scanResult = dlg.ShowTransfer(item, WIA.FormatID.wiaFormatPNG, true);

                if(scanResult != null)      // jesli skan sie wykonal zwroci zeskanowany obraz
                {
                    var imageFile = (ImageFile)scanResult;

                    // Zwrocenie imageFile
                    return imageFile;
                }
            }
            catch (COMException e)
            {
                // Wyswietlenie komunikatow
                Console.WriteLine(e.ToString());

                uint errorCode = (uint)e.ErrorCode;

                // Czesto wyswietlane komunikaty
                if (errorCode ==  0x80210006)
                {
                    MessageBox.Show("The scanner is busy or isn't ready");
                }else if(errorCode == 0x80210064)
                {
                    MessageBox.Show("The scanning process has been cancelled.");
                }else
                {
                    MessageBox.Show("A non catched error occurred, check the console","Error",MessageBoxButtons.OK);
                }
            }

            return new ImageFile();
        }

        /// <summary>
        /// Skanowanie obrazu w formacie JPEG
        /// </summary>
        /// <returns></returns>
        public ImageFile ScanJPEG()
        {
            // Wyswietlenie dostepnych urzadzen,
            // nastepnie wybieramy urzadzenie,
            // laczymy sie z urzadzeniem, mamy mozliwosc wykonania skanu
            var device = this._deviceInfo.Connect();
            CommonDialogClass dlg = new CommonDialogClass();

            var item = device.Items[1];

            try
            {
                AdjustScannerSettings(item, resolution, 0, 0, width_pixel, height_pixel, 0, 0, color_mode);

                object scanResult = dlg.ShowTransfer(item, WIA.FormatID.wiaFormatJPEG, true);

                if (scanResult != null)
                {
                    var imageFile = (ImageFile)scanResult;
                    
                    return imageFile;
                }
            }
            catch (COMException e)
            {
                
                Console.WriteLine(e.ToString());

                uint errorCode = (uint)e.ErrorCode;

                if (errorCode == 0x80210006)
                {
                    MessageBox.Show("The scanner is busy or isn't ready");
                }
                else if (errorCode == 0x80210064)
                {
                    MessageBox.Show("The scanning process has been cancelled.");
                }
                else
                {
                    MessageBox.Show("A non catched error occurred, check the console", "Error", MessageBoxButtons.OK);
                }
            }

            return new ImageFile();
        }

        /// <summary>
        /// Skanowanie obrazu w formacie TIFF
        /// </summary>
        /// <returns></returns>
        public ImageFile ScanTIFF()
        {
            // Wyswietlenie dostepnych urzadzen,
            // nastepnie wybieramy urzadzenie,
            // laczymy sie z urzadzeniem, mamy mozliwosc wykonania skanu
            var device = this._deviceInfo.Connect();
            CommonDialogClass dlg = new CommonDialogClass();

            var item = device.Items[1];

            try
            {
                AdjustScannerSettings(item, resolution, 0, 0, width_pixel, height_pixel, 0, 0, color_mode);

                object scanResult = dlg.ShowTransfer(item, WIA.FormatID.wiaFormatTIFF, true);

                if (scanResult != null)
                {
                    var imageFile = (ImageFile)scanResult;

                  
                    return imageFile;
                }
            }
            catch (COMException e)
            {

                Console.WriteLine(e.ToString());

                uint errorCode = (uint)e.ErrorCode;

                if (errorCode == 0x80210006)
                {
                    MessageBox.Show("The scanner is busy or isn't ready");
                }
                else if (errorCode == 0x80210064)
                {
                    MessageBox.Show("The scanning process has been cancelled.");
                }
                else
                {
                    MessageBox.Show("A non catched error occurred, check the console", "Error", MessageBoxButtons.OK);
                }
            }

            return new ImageFile();
        }

        /// <summary>
        /// Dostosowanie parametrow skanera
        /// </summary>
        /// <param name="scannnerItem"></param>
        /// <param name="scanResolutionDPI">Ustawienie rozdzielczosci DPI na 150>
        /// <param name="scanStartLeftPixel"></param>
        /// <param name="scanStartTopPixel"></param>
        /// <param name="scanWidthPixels"></param>
        /// <param name="scanHeightPixels"></param>
        /// <param name="brightnessPercents"></param>
        /// <param name="contrastPercents">Modyfikacja kontrastu</param>
        /// <param name="colorMode">Ustawienie skanowania: kolorowe lub czarno - biale</param>
        private static void AdjustScannerSettings(IItem scannnerItem, int scanResolutionDPI, int scanStartLeftPixel, int scanStartTopPixel, int scanWidthPixels, int scanHeightPixels, int brightnessPercents, int contrastPercents, int colorMode)
        {
            const string WIA_SCAN_COLOR_MODE = "6146";
            const string WIA_HORIZONTAL_SCAN_RESOLUTION_DPI = "6147";
            const string WIA_VERTICAL_SCAN_RESOLUTION_DPI = "6148";
            const string WIA_HORIZONTAL_SCAN_START_PIXEL = "6149";
            const string WIA_VERTICAL_SCAN_START_PIXEL = "6150";
            const string WIA_HORIZONTAL_SCAN_SIZE_PIXELS = "6151";
            const string WIA_VERTICAL_SCAN_SIZE_PIXELS = "6152";
            const string WIA_SCAN_BRIGHTNESS_PERCENTS = "6154";
            const string WIA_SCAN_CONTRAST_PERCENTS = "6155";
            SetWIAProperty(scannnerItem.Properties, WIA_HORIZONTAL_SCAN_RESOLUTION_DPI, scanResolutionDPI); 
            SetWIAProperty(scannnerItem.Properties, WIA_VERTICAL_SCAN_RESOLUTION_DPI, scanResolutionDPI);
            SetWIAProperty(scannnerItem.Properties, WIA_HORIZONTAL_SCAN_START_PIXEL, scanStartLeftPixel);
            SetWIAProperty(scannnerItem.Properties, WIA_VERTICAL_SCAN_START_PIXEL, scanStartTopPixel);
            SetWIAProperty(scannnerItem.Properties, WIA_HORIZONTAL_SCAN_SIZE_PIXELS, scanWidthPixels);
            SetWIAProperty(scannnerItem.Properties, WIA_VERTICAL_SCAN_SIZE_PIXELS, scanHeightPixels);
            SetWIAProperty(scannnerItem.Properties, WIA_SCAN_BRIGHTNESS_PERCENTS, brightnessPercents);
            SetWIAProperty(scannnerItem.Properties, WIA_SCAN_CONTRAST_PERCENTS, contrastPercents);
            SetWIAProperty(scannnerItem.Properties, WIA_SCAN_COLOR_MODE, colorMode);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="properties"></param>
        /// <param name="propName"></param>
        /// <param name="propValue"></param>
        private static void SetWIAProperty(IProperties properties, object propName, object propValue)
        {
            Property prop = properties.get_Item(ref propName);
            prop.set_Value(ref propValue);
        }

        /// <summary>
        /// Metoda ToString przekazuje informacje w postaci tekstowej, tutaj o nazwie skanera 
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return (string) this._deviceInfo.Properties["Name"].get_Value();
        }
         
    }
}
