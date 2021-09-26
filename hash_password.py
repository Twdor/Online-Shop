import bcrypt
import argparse

def hash_password(plain_text_password):
    hashed_bytes = bcrypt.hashpw(plain_text_password.encode("utf-8"), bcrypt.gensalt())
    return hashed_bytes.decode("utf-8")

parser = argparse.ArgumentParser()

parser.add_argument("plain_text_password")

args = parser.parse_args()

print(hash_password(args.plain_text_password))
