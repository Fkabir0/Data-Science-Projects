import wikipediaapi
from datasketch import MinHash
import nltk
from nltk.util import ngrams

# Download NLTK resources
nltk.download('punkt')

# Function to preprocess text
def preprocess_text(text):
    #Tokenizing 
    tokens = nltk.word_tokenize(text)
    n = 3 
    n_grams = list(ngrams(tokens, n))
    shingles = [' '.join(gram) for gram in n_grams]
    return shingles

#minhashing 
def compute_minhash(text):
    shingles = preprocess_text(text)
    minhash = MinHash(num_perm=128)
    for shingle in shingles:
        minhash.update(shingle.encode('utf8'))
    return minhash

#user agent for the wiki
user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
wiki_wiki = wikipediaapi.Wikipedia(user_agent=user_agent)

#getting two wikipedia pages
page1 = wiki_wiki.page("Leonardo da Vinci")
page2 = wiki_wiki.page("World War II")

#Computing MinHash for the articles
minhash1 = compute_minhash(page1.text)
minhash2 = compute_minhash(page2.text)

#Computing Jaccard similarity 
jaccard_similarity = minhash1.jaccard(minhash2)

#output Jaccard similarity
print("Jaccard similarity of the two articles:", jaccard_similarity)
