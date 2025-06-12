from transformers import AutoTokenizer, AutoModelForSequenceClassification, TextClassificationPipeline
import torch
import sys

model_name = "searle-j/kote_for_easygoing_people"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForSequenceClassification.from_pretrained(model_name)
pipe = TextClassificationPipeline(
    model=model,
    tokenizer=tokenizer,
    device=0 if torch.cuda.is_available() else -1,
    return_all_scores=True,
    function_to_apply="sigmoid"
)

text = sys.argv[1]
predictions = pipe(text)
top_emotion = max(predictions[0], key=lambda x: x['score'])['label']
print(top_emotion)
