javac -d . *.java

if [ $1 == 'Game' ]
then
  echo "Press WAD for movement and Shift to sprint"
  java Game
elif [ $1 == 'Editor' ]
then
  java MapEditor $2
fi

rm -f *.class