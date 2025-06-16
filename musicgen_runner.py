# musicgen_runner.py
from transformers import pipeline
import torch
import sys
import scipy.io.wavfile
import os

prompt = sys.argv[1]
duration = int(sys.argv[2])
output_path = sys.argv[3]

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
pipe = pipeline("text-to-audio", model="facebook/musicgen-large", device=0 if device.type == "cuda" else -1)

result = pipe(prompt, forward_params={"do_sample": True, "max_length": duration * 50})
scipy.io.wavfile.write(output_path, rate=result["sampling_rate"], data=result["audio"])
