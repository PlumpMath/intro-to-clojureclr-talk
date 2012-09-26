using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HockeyStats
{
    public static class ClojureClr
    {
        public static string GenerateHtml(string lname)
        {
            return export.html.CreateHtml(lname);
        }
    }
}
