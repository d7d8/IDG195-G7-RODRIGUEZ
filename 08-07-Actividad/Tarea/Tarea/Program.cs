using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tarea
{
    class Program
    { 
        static void Main(string[] args)
        {
            int[] array = new int[4];
            int total = 0;
            Random random = new Random();
                for (int x = 0; x < array.Length; x++)
                {
                    array[x] = random.Next(0, 100);
                }
                for (int x = 0; x < array.Length; x++)
                {
                    total += array[x];
                    Console.WriteLine(array[x]);
                }
            Console.WriteLine("El valor total es "+total);
            Console.ReadKey();
        }
    }
}
