using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReplExample
{
    public class MyClass
    {
        public long MyVal { get; set; }
        public string Greetings { get { return "Hi, From C#!!!"; } }

        public string GetTime()
        {
            return string.Format("The time is {0}", DateTime.Now.ToLongTimeString());
        }

        public void SayHi(string name)
        {
            Console.WriteLine("Hi, {0}!", name);
        }
    }
}
