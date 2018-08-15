using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tarea2
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] array = new int[4];
            Random random = new Random();
            for (int x = 0; x < array.Length; x++)
            {
                array[x] = random.Next(0, 100);
            }
            for (int x = 0; x < array.Length; x++)
            {
                bool flag = true;
                string respuesta = " no es primo";
                while (flag == true)
                {
                    if ((array[x] == 1)||(array[x] == 2))
                    {
                        respuesta = " es primo";
                        break;
                    }
                    if (array[x] % 2 == 0)
                    {
                        break;
                    }
                    for (int y = 3; y <= array[x]; y += 2)
                    {
                        
                        if (((array[x]%y) == 0) && (y != array[x]))
                        {
                            flag = false;
                            break;
                        }               
                    }
                    if (flag == true)
                    {
                        respuesta = " es primo";
                        break;
                    }
                }
                Console.WriteLine(array[x] + respuesta);
            }
            Console.ReadKey();
        }
    }
}
