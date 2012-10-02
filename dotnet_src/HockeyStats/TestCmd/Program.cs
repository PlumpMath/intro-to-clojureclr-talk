using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestCmd
{
    class Program
    {
        static void Main(string[] args)
        {
            var fileName = "ricci.html";

            // Call in the export.html/-CreateHtml function
            // get the HTML string
            var content = export.html.CreateHtml("Ricci");

            // writ the results to an html file
            System.IO.File.WriteAllText(fileName, content);

            // Display the created page.
            System.Diagnostics.Process.Start(fileName);
        }
    }
}
