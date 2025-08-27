package com.example.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class SolutionAdapter extends RecyclerView.Adapter<SolutionAdapter.SolutionViewHolder> {

    private Context context;
    private List<Question> questionList;
    private HashMap<Integer, Integer> userAnswers;

    public SolutionAdapter(Context context, List<Question> questionList, HashMap<Integer, Integer> userAnswers) {
        this.context = context;
        this.questionList = questionList;
        this.userAnswers = userAnswers;
    }

    @NonNull
    @Override
    public SolutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_solution, parent, false);
        return new SolutionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolutionViewHolder holder, int position) {
        Question q = questionList.get(position);

        holder.tvQuestion.setText((position + 1) + ". " + q.getQuestion());
        holder.tvOption1.setText("1. " + q.getOption1());
        holder.tvOption2.setText("2. " + q.getOption2());
        holder.tvOption3.setText("3. " + q.getOption3());
        holder.tvOption4.setText("4. " + q.getOption4());

        // Reset colors
        holder.tvOption1.setTextColor(Color.BLACK);
        holder.tvOption2.setTextColor(Color.BLACK);
        holder.tvOption3.setTextColor(Color.BLACK);
        holder.tvOption4.setTextColor(Color.BLACK);

        // Highlight correct answer
        int correct = q.getAnswer();
        switch (correct) {
            case 1: holder.tvOption1.setTextColor(Color.GREEN); break;
            case 2: holder.tvOption2.setTextColor(Color.GREEN); break;
            case 3: holder.tvOption3.setTextColor(Color.GREEN); break;
            case 4: holder.tvOption4.setTextColor(Color.GREEN); break;
        }

        // Highlight user answer if wrong
        if (userAnswers.containsKey(position)) {
            int userAns = userAnswers.get(position);
            if (userAns != correct) {
                switch (userAns) {
                    case 1: holder.tvOption1.setTextColor(Color.RED); break;
                    case 2: holder.tvOption2.setTextColor(Color.RED); break;
                    case 3: holder.tvOption3.setTextColor(Color.RED); break;
                    case 4: holder.tvOption4.setTextColor(Color.RED); break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    static class SolutionViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion, tvOption1, tvOption2, tvOption3, tvOption4;

        public SolutionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvOption1 = itemView.findViewById(R.id.tvOption1);
            tvOption2 = itemView.findViewById(R.id.tvOption2);
            tvOption3 = itemView.findViewById(R.id.tvOption3);
            tvOption4 = itemView.findViewById(R.id.tvOption4);
        }
    }
}
