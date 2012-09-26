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
            Console.WriteLine(HockeyStats.ClojureClr.GenerateHtml("Ricci"));
            //HockeyStats.ClojureClr.GetHtml();
        }
    }
}
