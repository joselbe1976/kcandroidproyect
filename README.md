# Waiters App Android
## Android Fundamentos - V keepCoding Bootcamp Mobiles

## Jose Luis Bustos Esteban

Development :
  1. Lenguaje Kotlin.
  2. Para  Smartphones y  Tablets
  3. SDK min: 16
  4. URL JSON Data: http://www.mocky.io/v2/5a09f1c02e0000b107489bd1?mocky-delay=2000ms
  7. Uso de botones flotantes en el App.
  5. Descripcion funciona:
    - Al arrancar la aplicacion de cargan los datos mostrarndo una barra de progreso, una vez descargados, se muestra las mesas.
    - Al seleccionar una mesa se muestra el contenido de la misma. Desde ahi se puede ejecutar la factura o añadir platos.
    - Al pulsar "Factura Pagada" se eliminan del modelo los platos asociados a la mesa
    - Al pulsar sobre la lista de un producto, se muestra una actividad detalle deonde se puede personalizar con los comentarios del cliente
  6. Uso de Fragments. Cuando se selecciona una mesa, se carga el fragment en el lado derecho. No uso Pager con el fin de realizar y consolidar el manejod e fragments.
  7. Uso en alguna pantallas de extensions de Kotlin para el acceso a los elementos de la UI y ClickListeners, sin necesidad del uso de findViewById...



