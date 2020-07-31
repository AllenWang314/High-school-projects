#test = open("milkorder.in", "w")
#test.write("9 4 5\n3 4 7 2\n5 3\n3 1\n4 5\n9 6\n2 8")
#test.close()

inFile = open("milkorder.in", "r")
outFile = open("milkorder.out", "w")
#another = open("4.out", "r")

info = inFile.readline() # read basic info
info = info.split()
arr = [0]*int(info[0]) # create the array
hier = inFile.readline().split() # read hier line and split it

for i in range(int(info[2])): # read all the given spots
    spec = inFile.readline().split()
    arr[int(spec[1])-1] = int(spec[0]) # set location as cow

if "1" not in hier:
    i = len(hier) - 1 # i is counter for hier start at end
    curr = len(arr) - 1 # curr is counter for arr start at end
    while (i > -1): # while i is > -1
        if int(hier[i]) in arr: # if the current number in hier is in arr
            curr = arr.index(int(hier[i]))-1 # we make curr 1 less than that place
            i -= 1 # we decrease i to move to next number
        elif arr[curr] != 0: #otherwise we can put this number down if curr is not 0 we just dec curr
            curr -= 1
        else:
            arr[curr] = int(hier[i])
            i -= 1
            curr -= 1
else:
    i = 0 # i is counter for hier start at end
    curr = 0 # curr is counter for arr start at end
    while (i < len(hier)): # while i is > -1
        if int(hier[i]) in arr: # if the current number in hier is in arr
            curr = arr.index(int(hier[i]))+1 # we make curr 1 less than that place
            i += 1 # we decrease i to move to next number
        elif arr[curr] != 0: #otherwise we can put this number down if curr is not 0 we just dec curr
            curr += 1
        else:
            arr[curr] = int(hier[i])
            i += 1
            curr += 1
    
if 1 in arr:
    ans = arr.index(1) + 1
else:
    ans = arr.index(0) + 1

#print(arr)
#print(ans)
#print(another.readline())
#another.close()
outFile.write(str(ans))
inFile.close()
outFile.close()
