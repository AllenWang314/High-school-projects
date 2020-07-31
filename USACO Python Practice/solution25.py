'''
test = open("shuffle.in", "w")
test.write("8\n1 4 1 5 4 5 1 8")
test.close()
'''

inFile = open("shuffle.in", "r")
outFile = open("shuffle.out", "w")
num = int(inFile.readline())
shuff = inFile.readline().split()
for i in range(num):
	shuff[i] = int(shuff[i]) - 1
reach = set([])
processed = set([])
ind = set(range(num))



while(len(ind) > 0):
	current = next(iter(ind))
	temp = [current]
	while(temp != []):
		if shuff[current] in processed:
			for element in temp:
				processed.add(element)
				ind.remove(element)
				# do nothing to reach
			temp = []
		elif shuff[current] in temp:
			index = temp.index(shuff[current])
			for i in range(index):
				processed.add(temp[i])
				ind.remove(temp[i])
				# do nothing to reach
			for i in range(index, len(temp)):
				processed.add(temp[i])
				ind.remove(temp[i])
				reach.add(temp[i])
			temp = []
		else:
			temp.append(shuff[current])
			current = shuff[current]



	'''
	pop out an ind,
	create a temp set
	if ind's next element is processed, toss everything away
	if ind's next element is on the list, throw everything before away, and add everything after to the
	if ind's next element is not on list, add it to the list
	update reach, processed, ind, and current appropriately


	'''
'''
print(reach)
print(ind)
print(shuff)
print(len(reach))
'''

outFile.write(str(len(reach)))
inFile.close()
outFile.close()