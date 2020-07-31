#test = open("guess.in", "w")
#test.write("4\nbird 2 flies eatsworms\n\
#           cow 4 eatsgrass isawesome makesmilk goesmoo\n\
#           sheep 1 eatsgrass\n\
#           goat 2 makesmilk eatsgrass")
#test.close()

inFile = open("guess.in", "r")
outFile = open("guess.out", "w")
N = int(inFile.readline())

arr = []

for i in range(N):
    line = inFile.readline().split()[2:]
    arr.append(line)

#print(arr)

yes = [0]*N

for i in range(N):
    for trait in arr[i]:
        count = 0
        for j in range(N):
            if trait in arr[j]:
                count += 1
        if count > 1:
            yes[i] += 1

outFile.write(str(max(yes) + 1))
inFile.close()
outFile.close()
