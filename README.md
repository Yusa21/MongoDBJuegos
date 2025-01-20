# Preguntas y Respuestas

### a) ¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?

La principales ventajas es que es mucho más fácil de implementar y escalarla es mucho más sencilla que una base de datos relacional. El inconveniente principal es que la consistencia de los datos no está garantizada por la base de datos en sí y se tiene que cuidar en la implementación.

---

### b) ¿Cuáles han sido los principales problemas que has tenido al desarrollar la aplicación?

Es complicado asegurarse de que nunca se rompe la consistencia de datos, es más, he estado a punto de no comprobar que los títulos sean únicos y no nulos cuando se actualiza un juego porque la base de datos en sí no da problemas.

---

### c) ¿Cómo solucionarías el problema de la inconsistencia de datos en una base de datos documental? 
(El hecho de que en los documentos de una colección no sea obligatorio seguir un esquema único)

Teniendo mucho cuidado de la forma en la que se introducen, modifican o se borran los datos de la base de datos.  
En parte esto también se tiene que cuidar cuando se implementa una base de datos relacional ya que un error inesperado al no respetar el formato también puede romper el programa. 

Sin embargo, en este caso es más complicado asegurarse de que este tipo de fallos no sucede porque muchos de estos datos los acepta la base de datos sin dar ningún error, así que a la hora de hacer pruebas hay que ser más concienzudo.
