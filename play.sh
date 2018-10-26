javac -classpath . -d . *.java

if [ $1 == 'Game' ]
then
  echo "Press WAD for movement and Shift to sprint"
  java Game Mario
elif [ $1 == 'Editor' ]
then
  java Game Editor $2
fi

rm -f *.class