inFile = open("outofplace.in", "r")
outFile = open("outofplace.out", "w")

numCow = int(inFile.readline())
arr = []
changed = False

for i in range(numCow):
    arr.append(int(inFile.readline()))

if arr[0] > arr[1]:
    wrongCow = 0
    moveUp = True
    changed = True
elif arr[numCow-1] < arr[numCow-2]:
    wrongCow = numCow-1
    moveUp = False
    changed = True
else:
    for i in range(1,numCow-1):
        if arr[i-1] <= arr[i+1] and arr[i] > arr[i+1]:
            wrongCow = i
            moveUp = True
            changed = True
        elif arr[i-1] <= arr[i+1] and arr[i] < arr[i-1]:
            wrongCow = i
            moveUp = False
            changed = True

moves = 0
if moveUp:
    i = wrongCow
    while i < numCow-1:
        if arr[i+1] < arr[wrongCow] and arr[i] < arr[i+1]:
            moves += 1
        i += 1
else:
    i = wrongCow
    while i > 0:
        if arr[wrongCow] < arr[i-1] and arr[i] > arr[i-1]:
            moves += 1
        i -= 1
        
if changed:
    moves += 1

outFile.write(str(moves))
outFile.close()
inFile.close()
    

