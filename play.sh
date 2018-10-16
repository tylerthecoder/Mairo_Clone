javac *.java

if [ $1 == 'Game' ]
then
  java Game
elif [ $1 == 'Editor' ]
then
  java MapEditor $2
fi

rm -f *.class