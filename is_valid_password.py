import bcrypt
import argparse

def is_valid_password(plain_text_password, hashed_password):
    hashed_bytes_password = hashed_password.encode("utf-8")
    return bcrypt.checkpw(plain_text_password.encode("utf-8"), hashed_bytes_password)

parser = argparse.ArgumentParser()

parser.add_argument("plain_text_password")
parser.add_argument("hashed_password")

args = parser.parse_args()

print(is_valid_password(args.plain_text_password, args.hashed_password))