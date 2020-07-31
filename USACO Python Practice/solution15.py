#test = open("sleepy.in", "w")
#test.write("4\n1 2 4 3")
#test.close()

inFile = open("sleepy.in", "r")
outFile = open("sleepy.out", "w")
inFile.readline()
arr = inFile.readline().split()
i = len(arr)-1

while i >= 0 and int(arr[i]) > int(arr[i-1]):
    i -= 1

outFile.write(str(i))
inFile.close()
outFile.close()
