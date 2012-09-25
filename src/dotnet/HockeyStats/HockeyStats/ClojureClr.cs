using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HockeyStats
{
    public static class ClojureClr
    {
        public static void GetHtml()
        {
            Console.WriteLine(export.html.CreateHtml("Ricci"));
        }
    }
}
