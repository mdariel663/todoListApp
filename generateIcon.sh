#!/bin/bash

# Verifica si ImageMagick está instalado
if ! command -v convert &> /dev/null
then
    echo "ImageMagick no está instalado. Por favor, instálalo primero."
    exit
fi

# Ruta a la imagen de origen
SOURCE_IMAGE="$1"

# Directorio de destino
DEST_DIR="app/src/main/res"

# Crea los directorios si no existen
mkdir -p $DEST_DIR/mipmap-mdpi
mkdir -p $DEST_DIR/mipmap-hdpi
mkdir -p $DEST_DIR/mipmap-xhdpi
mkdir -p $DEST_DIR/mipmap-xxhdpi
mkdir -p $DEST_DIR/mipmap-xxxhdpi

# Genera los iconos en diferentes resoluciones
convert $SOURCE_IMAGE -resize 48x48 $DEST_DIR/mipmap-mdpi/ic_launcher.webp
convert $SOURCE_IMAGE -resize 72x72 $DEST_DIR/mipmap-hdpi/ic_launcher.webp
convert $SOURCE_IMAGE -resize 96x96 $DEST_DIR/mipmap-xhdpi/ic_launcher.webp
convert $SOURCE_IMAGE -resize 144x144 $DEST_DIR/mipmap-xxhdpi/ic_launcher.webp
convert $SOURCE_IMAGE -resize 192x192 $DEST_DIR/mipmap-xxxhdpi/ic_launcher.webp

# Genera los iconos redondos en diferentes resoluciones
convert $SOURCE_IMAGE -resize 48x48 $DEST_DIR/mipmap-mdpi/ic_launcher_round.webp
convert $SOURCE_IMAGE -resize 72x72 $DEST_DIR/mipmap-hdpi/ic_launcher_round.webp
convert $SOURCE_IMAGE -resize 96x96 $DEST_DIR/mipmap-xhdpi/ic_launcher_round.webp
convert $SOURCE_IMAGE -resize 144x144 $DEST_DIR/mipmap-xxhdpi/ic_launcher_round.webp
convert $SOURCE_IMAGE -resize 192x192 $DEST_DIR/mipmap-xxxhdpi/ic_launcher_round.webp

echo "Iconos generados exitosamente en $DEST_DIR"
